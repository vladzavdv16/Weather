package com.light.notes.weather.data.database.day

import androidx.lifecycle.LiveData
import androidx.room.*
import com.light.notes.weather.ui.main.model.DayCellModel

@Dao
interface DayDao {

    @Query("SELECT * from table_day")
    fun getAllDay(): LiveData<DayCellModel>

    @Query("SELECT COUNT(id) FROM table_day")
    fun countHours(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(allDay: DayCellModel)

    @Delete
    suspend fun delete(deleteAllDay: DayCellModel)

    @Update
    suspend fun update(updateAllDay: DayCellModel)
}