package com.light.notes.weather.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.light.notes.weather.R
import com.light.notes.weather.databinding.FragmentMainBinding
import com.light.notes.weather.ui.main.hours_adapter.HoursAdapter
import com.light.notes.weather.ui.main.hours_adapter.HoursCellModel
import com.light.notes.weather.ui.main.model.DayCellModel
import com.light.notes.weather.ui.main.week_adapter.WeekAdapter
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel
import com.light.notes.weather.util.APP_ACTIVITY
import com.light.notes.weather.util.Permission
import com.light.notes.weather.util.showToast
import com.squareup.picasso.Picasso


class MainFragment : Fragment(R.layout.fragment_main), LocationListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var hoursAdapter: HoursAdapter
    private lateinit var weekAdapter: WeekAdapter
    private var viewModel: MainViewModel? = null
    private lateinit var locationManager: LocationManager
    private lateinit var observerListWeek: Observer<List<WeekCellModel>>
    private lateinit var observerListHours: Observer<List<HoursCellModel>>
    private lateinit var observerDay: Observer<DayCellModel>
    private lateinit var observerDayAll: Observer<DayCellModel>

    companion object {
        var LAT: String = ""
        var LON: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel?.initDatabase()
        hoursAdapter = HoursAdapter()
        weekAdapter = WeekAdapter()
        Permission(requireContext()).permission()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel!!.all()
        observerListWeek = Observer {
            if (it.isNotEmpty()) {
                weekAdapter.weekData(it)
//                viewModel!!.deleteWeek(it)
            }
        }
        observerListHours = Observer {
            if (it.isNotEmpty()) {
                hoursAdapter.hoursData(it)
//                viewModel!!.deleteHours(it)
            } else {
                binding.welcomeText.visibility = View.VISIBLE
            }
        }
        observerDayAll = Observer {
            if (it != null) {
                APP_ACTIVITY.title = it.name
                binding.tvDescription.text = it.description
                binding.tvTemp.text = "${it.temp}°C"
                Picasso.get()
                    .load("https://openweathermap.org/img/wn/" + it.icon + "@2x.png")
                    .into(binding.image)
//                viewModel!!.deleteDay(it)
            }
        }
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        getLocation()
    }

    override fun onResume() {
        super.onResume()
        init()
        viewModel?.allWeek?.observe(this, observerListWeek)
        viewModel?.allHours?.observe(this, observerListHours)
        viewModel!!.allDay.observe(this, observerDayAll)

        binding.swipeRefresh.setOnRefreshListener {
            if (LAT.isEmpty()) {
                getLocation()
                binding.swipeRefresh.isRefreshing = false
            } else {
                setupData()
                viewModel?.fetchRequest()
                viewModel!!.day.observe(this) { it ->
                    if (it != null) {
                        println(it.name)
                        viewModel?.countDay?.observe(this) { count ->
                            println(count)
                            if (count == 0) {
                                viewModel!!.insertDay(it)
                                APP_ACTIVITY.title = it.name
                                binding.tvDescription.text = it.description
                                binding.tvTemp.text = "${it.temp}°C"
                                Picasso.get()
                                    .load("https://openweathermap.org/img/wn/" + it.icon + "@2x.png")
                                    .into(binding.image)
                            } else {
                                observerDayAll = Observer { iti ->
                                    if (iti != null)
                                        viewModel!!.deleteDay(iti)
                                    println(iti)
                                    binding.swipeRefresh.isRefreshing = false
                                }
                            }
                            viewModel?.allDay?.observe(this, observerDayAll)
                            viewModel!!.allDay.removeObserver(observerDayAll)

                        }
                    }
                }
            }

        }
    }

    private fun init() {
        binding.recyclerHours.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewModel?.isLoading?.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        binding.recyclerWeek.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.recyclerWeek.adapter = weekAdapter
        binding.recyclerHours.adapter = hoursAdapter
    }


    private fun setupData() {
        binding.welcomeText.visibility = View.GONE
        viewModel?.hours?.observe(this, Observer { it ->
            if (it.isNotEmpty()) {
                viewModel!!.allHours.removeObserver(observerListHours)
                hoursAdapter.hoursData(it)
                viewModel?.countHours?.observe(this, Observer { count ->
                    if (count == 0) {
                        viewModel!!.insertHours(it)
                    } else {
                        observerListHours = Observer { iti ->
                            if (iti != null)
                                viewModel!!.deleteHours(iti)
                        }
                    }
                    viewModel!!.allHours.observe(this, observerListHours)
                    viewModel!!.allHours.removeObserver(observerListHours)
                })
            }
        })

        viewModel?.week?.observe(this, Observer { it ->
            if (it.isNotEmpty()) {
                viewModel!!.allWeek.removeObserver(observerListWeek)
                weekAdapter.weekData(it)
                viewModel?.countWeek?.observe(this, Observer { count ->
                    if (count == 0) {
                        viewModel!!.insertWeek(it)
                    } else {
                        observerListWeek = Observer { iti ->
                            if (iti != null)
                                viewModel!!.deleteWeek(iti)
                        }
                    }
                    viewModel!!.allWeek.observe(this, observerListWeek)
                    viewModel!!.allWeek.removeObserver(observerListWeek)
                })
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onLocationChanged(p0: Location) {
        LAT = p0.latitude.toString()
        LON = p0.longitude.toString()
        println("$LON $LAT")
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            ) && locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 20000, 0F, this
            )
        } else {
            showToast("Включите GPS")
        }
    }

    override fun onStop() {
        super.onStop()

//        viewModel?.allWeek?.removeObserver(observerListWeek)
//        viewModel!!.allHours.removeObserver(observerListHours)
//        viewModel!!.allDay.removeObserver(observerDayAll)
    }
}


