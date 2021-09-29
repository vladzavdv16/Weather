package com.light.notes.weather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.light.notes.weather.domain.model.DayModel
import com.light.notes.weather.domain.repository.DayRepoImpl
import com.light.notes.weather.domain.repository.HoursRepoImpl
import com.light.notes.weather.domain.repository.WeekRepoImpl
import com.light.notes.weather.ui.main.hours_adapter.HoursCellModel
import com.light.notes.weather.ui.main.hours_adapter.mapToUIHours
import com.light.notes.weather.ui.main.model.DayCellModel
import com.light.notes.weather.ui.main.model.mapToUI
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel
import com.light.notes.weather.ui.main.week_adapter.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val hoursRepo = HoursRepoImpl()
    private val weekRepo = WeekRepoImpl()
    private val dayRepo = DayRepoImpl()

    private val _hours = MutableLiveData<List<HoursCellModel>>().apply {
        value = ArrayList()
    }

    private val _week = MutableLiveData<List<WeekCellModel>>().apply {
        value = ArrayList()
    }

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _day = MutableLiveData<DayCellModel>().apply {

    }

    val isLoading: LiveData<Boolean> = _isLoading
    val hours: LiveData<List<HoursCellModel>> = _hours
    val week: LiveData<List<WeekCellModel>> = _week
    val day: LiveData<DayCellModel> = _day


    fun fetchRequest() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            withContext(Dispatchers.Default) {
                val hours = hoursRepo.fetchHours()
                val week = weekRepo.fetchWeek()
                val day = dayRepo.fetchDay()
                _isLoading.postValue(false)
                _hours.postValue(hours.map {
                    it.mapToUIHours()
                })
                _week.postValue(week.map {
                    it.mapToUI()
                })
                _day.postValue(day.mapToUI())
            }
        }
    }

}