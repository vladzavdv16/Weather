package com.light.notes.weather.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.light.notes.weather.data.database.AppRoomDatabase
import com.light.notes.weather.data.database.week.WeekDatabaseRepositoryImpl
import com.light.notes.weather.domain.repository.DayRepoImpl
import com.light.notes.weather.domain.repository.HoursRepoImpl
import com.light.notes.weather.domain.repository.WeekRepoImpl
import com.light.notes.weather.ui.main.hours_adapter.HoursCellModel
import com.light.notes.weather.ui.main.hours_adapter.mapToUIHours
import com.light.notes.weather.ui.main.model.DayCellModel
import com.light.notes.weather.ui.main.model.mapToUI
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel
import com.light.notes.weather.ui.main.week_adapter.mapToUI
import com.light.notes.weather.util.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    private val hoursRepo = HoursRepoImpl()
    private val weekRepo = WeekRepoImpl()
    private val dayRepo = DayRepoImpl()

    lateinit var allWeek: LiveData<List<WeekCellModel>>

    lateinit var count: LiveData<Int>

    fun initDatabase() {
        val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
        REPOSITORY = WeekDatabaseRepositoryImpl(dao)
    }

    private val _hours = MutableLiveData<List<HoursCellModel>>().apply {
        value = ArrayList()
    }

    private val _week = MutableLiveData<List<WeekCellModel>>().apply {
        value = ArrayList()
    }

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _day = MutableLiveData<DayCellModel>().apply {}

    val isLoading: LiveData<Boolean> = _isLoading
    val hours: LiveData<List<HoursCellModel>> = _hours
    val week: LiveData<List<WeekCellModel>> = _week
    val day: LiveData<DayCellModel> = _day

    fun insert(allWeek: List<WeekCellModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(allWeek)
        }
    }

    fun delete(deleteAllWeek: List<WeekCellModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(deleteAllWeek)
        }
    }

    fun update(update: List<WeekCellModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(update)
        }
    }

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

    fun all() {
        val dao = AppRoomDatabase.getInstance(context).getAppRoomDao()
        REPOSITORY = WeekDatabaseRepositoryImpl(dao)
        allWeek = REPOSITORY.allWeek
        count = REPOSITORY.coinsCount
    }

}