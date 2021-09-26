package com.light.notes.weather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.light.notes.weather.domain.repository.HoursRepoImpl
import com.light.notes.weather.domain.repository.WeekRepoImpl
import com.light.notes.weather.ui.main.hours_adapter.HoursCellModel
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val hoursRepo = HoursRepoImpl()
    private val weekRepo = WeekRepoImpl()

    private val _hours = MutableLiveData<List<HoursCellModel>>().apply {
        value = ArrayList()
    }

    private val _week = MutableLiveData<List<WeekCellModel>>().apply {
        value = ArrayList()
    }

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }


    val isLoading: LiveData<Boolean> = _isLoading
    val hours: LiveData<List<HoursCellModel>> = _hours
    val week: LiveData<List<WeekCellModel>> = _week


    fun fetchRequest() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            withContext(Dispatchers.Default) {
                val hours = hoursRepo.fetchHours()
                val week = weekRepo.fetchWeek()
                _isLoading.postValue(false)
                _hours.postValue(hours.map {
                    HoursCellModel(
                        time = it.time, temp = it.temp
                    )
                })
                _week.postValue(week.map {
                    WeekCellModel(
                        date = it.date,
                        description = it.description,
                        tempDay = it.tempDay,
                        tempNight = it.tempNight
                    )
                })
            }
        }

    }


}