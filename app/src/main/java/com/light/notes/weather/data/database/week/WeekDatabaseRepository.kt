package com.light.notes.weather.data.database.week


import androidx.lifecycle.LiveData
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel

interface WeekDatabaseRepository {

    val allWeek: LiveData<List<WeekCellModel>>

    val coinsCount: LiveData<Int>

    suspend fun insert(allWeek: List<WeekCellModel>)

    suspend fun delete(deleteAllWeek: List<WeekCellModel>)

    suspend fun update(updateWeek: List<WeekCellModel>)

}