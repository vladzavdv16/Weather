package com.light.notes.weather.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.light.notes.weather.R
import com.light.notes.weather.databinding.FragmentMainBinding
import com.light.notes.weather.ui.main.hours_adapter.HoursAdapter
import com.light.notes.weather.ui.main.week_adapter.WeekAdapter
import com.light.notes.weather.util.APP_ACTIVITY
import com.squareup.picasso.Picasso
import okhttp3.internal.format

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var hoursAdapter: HoursAdapter
    private lateinit var weekAdapter: WeekAdapter
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hoursAdapter = HoursAdapter()
        weekAdapter = WeekAdapter()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        setHasOptionsMenu(true)

    }

    override fun onResume() {
        super.onResume()

        setupData()
        viewModel?.day!!.observe(this) {
            APP_ACTIVITY.title = it.name
            binding.tvDescription.text = it.description
            binding.tvTemp.text = "${it.temp}°C"
            Picasso.get()
                .load("https://openweathermap.org/img/wn/" + it.icon + "@2x.png")
                .into(binding.image)
        }
        binding.recyclerHours.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewModel?.isLoading?.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        binding.recyclerWeek.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.recyclerHours.adapter = hoursAdapter
        binding.recyclerWeek.adapter = weekAdapter

        viewModel?.fetchRequest()
    }

    private fun setupData() {

        viewModel?.hours?.observe(this, Observer {
            if (it.isNotEmpty()) {
                hoursAdapter.hoursData(it)
            }
        })

        viewModel?.week?.observe(this, Observer {
            if (it.isNotEmpty()) {
                weekAdapter.weekData(it)
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


}