package com.light.notes.weather.data.database.hours

import androidx.lifecycle.LiveData
import com.light.notes.weather.ui.main.hours_adapter.HoursCellModel


interface HoursDatabaseRepository {

    val allHours: LiveData<List<HoursCellModel>>

    val countHours: LiveData<Int>

    suspend fun insert(allHours: List<HoursCellModel>)

    suspend fun delete(deleteAllHours: List<HoursCellModel>)

    suspend fun update(updateAllHours: List<HoursCellModel>)
}