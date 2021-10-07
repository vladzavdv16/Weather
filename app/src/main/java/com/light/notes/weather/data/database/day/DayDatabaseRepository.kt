package com.light.notes.weather.data.database.day

import androidx.lifecycle.LiveData
import com.light.notes.weather.ui.main.model.DayCellModel
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel

interface DayDatabaseRepository {

    val allDay: LiveData<DayCellModel>

    val coinsCount: LiveData<Int>

    suspend fun insert(allDay: DayCellModel)

    suspend fun delete(deleteAllDay: DayCellModel)

    suspend fun update(updateDay: DayCellModel)
}