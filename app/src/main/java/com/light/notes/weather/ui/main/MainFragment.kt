package com.light.notes.weather.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.light.notes.weather.R
import com.light.notes.weather.databinding.FragmentMainBinding
import com.light.notes.weather.ui.main.hours_adapter.HoursAdapter
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
//                viewModel!!.delete(it)
                weekAdapter.weekData(it)
            }
        }
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        getLocation()
    }

    override fun onResume() {
        super.onResume()
        println()
        init()
        binding.recyclerWeek.adapter = weekAdapter

        viewModel?.allWeek?.observe(this, observerListWeek)

        binding.swipeRefresh.setOnRefreshListener {
            if (LAT.isEmpty()) {
                binding.swipeRefresh.isRefreshing = false
                showToast("Error")
            } else {
                setupData()
//                viewModel?.allWeek?.observe(this, observerList)
                viewModel?.day!!.observe(this) {
                    APP_ACTIVITY.title = it.name
                    binding.tvDescription.text = it.description
                    binding.tvTemp.text = "${it.temp}°C"
                    Picasso.get()
                        .load("https://openweathermap.org/img/wn/" + it.icon + "@2x.png")
                        .into(binding.image)
                }
                init()
                binding.recyclerHours.adapter = hoursAdapter
                binding.recyclerWeek.adapter = weekAdapter

                viewModel?.fetchRequest()
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

    }


    private fun setupData() {
        viewModel?.hours?.observe(this, Observer {
            if (it.isNotEmpty()) {
                hoursAdapter.hoursData(it)
            }
        })


        viewModel?.week?.observe(this, Observer { it ->
            if (it.isNotEmpty()) {
                viewModel!!.allWeek.removeObserver(observerListWeek)
                weekAdapter.weekData(it)
                viewModel?.count?.observe(this, Observer { count ->
                    if (count == 0) {
                        viewModel!!.insert(it)
                    } else {
                        viewModel?.update(it)
                    }
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
        if (p0 != null) {
            LAT = p0.latitude.toString()
            LON = p0.longitude.toString()
            println("$LON $LAT")
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            ) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 20000, 0F, this
            )
        } else {
            showToast("Для получения местоположения включите GPS")
        }
    }

}