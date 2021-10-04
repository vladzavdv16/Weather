package com.light.notes.weather.data.database.hours

import androidx.lifecycle.LiveData
import androidx.room.*
import com.light.notes.weather.ui.main.hours_adapter.HoursCellModel
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel


@Dao
interface HoursDao {

    @Query("SELECT * from table_hours")
    fun getAllHours(): LiveData<List<HoursCellModel>>

    @Query("SELECT COUNT(id) FROM table_hours")
    fun countHours(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(allHours: List<HoursCellModel>)

    @Delete
    suspend fun delete(deleteAllHours: List<HoursCellModel>)

    @Update
    suspend fun update(updateAllHours: List<HoursCellModel>)
}