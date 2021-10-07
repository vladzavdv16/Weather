package com.light.notes.weather.data.database.day

import androidx.lifecycle.LiveData
import com.light.notes.weather.ui.main.model.DayCellModel
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel

class DayDatabaseRepositoryImpl(private val dao: DayDao) : DayDatabaseRepository {


    override val allDay: LiveData<DayCellModel>
        get() = dao.getAllDay()

    override val coinsCount: LiveData<Int>
        get() = dao.countHours()

    override suspend fun insert(allDay: DayCellModel) {
        dao.insert(allDay)
    }

    override suspend fun delete(deleteAllDay: DayCellModel) {
        dao.delete(deleteAllDay)
    }

    override suspend fun update(updateDay: DayCellModel) {
        dao.update(updateDay)
    }
}