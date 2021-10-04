package com.light.notes.weather.data.database.hours

import androidx.lifecycle.LiveData
import com.light.notes.weather.ui.main.hours_adapter.HoursCellModel

class HoursDatabaseRepositoryImpl(private val dao: HoursDao) : HoursDatabaseRepository {


    override val allHours: LiveData<List<HoursCellModel>>
        get() = dao.getAllHours()

    override val countHours: LiveData<Int>
        get() = dao.countHours()


    override suspend fun insert(allHours: List<HoursCellModel>) {
        dao.insert(allHours)
    }

    override suspend fun delete(deleteAllHours: List<HoursCellModel>) {
        dao.delete(deleteAllHours)
    }

    override suspend fun update(updateAllHours: List<HoursCellModel>) {
        dao.update(updateAllHours)
    }


}