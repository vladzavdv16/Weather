package com.light.notes.weather.data.database.week

import androidx.lifecycle.LiveData
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel

class WeekDatabaseRepositoryImpl(private val dao: Dao) : WeekDatabaseRepository {


    override val allWeek: LiveData<List<WeekCellModel>>
        get() = dao.getAllWeek()


    override val coinsCount: LiveData<Int>
        get() = dao.coinsCount()

    override suspend fun insert(allWeek: List<WeekCellModel>) {
        dao.insert(allWeek)
    }

    override suspend fun delete(deleteAllWeek: List<WeekCellModel>) {
        dao.delete(deleteAllWeek)
    }

    override suspend fun update(updateWeek: List<WeekCellModel>) {
        dao.update(updateWeek)
    }
}