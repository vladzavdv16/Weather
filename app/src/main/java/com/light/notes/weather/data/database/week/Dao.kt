package com.light.notes.weather.data.database.week

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel

@Dao
interface Dao {

    @Query("SELECT * from table_week")
    fun getAllWeek(): LiveData<List<WeekCellModel>>

    @Query("SELECT COUNT(id) FROM table_week")
    fun coinsCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(allWeek: List<WeekCellModel>)

    @Delete
    suspend fun delete(deleteAllWeek: List<WeekCellModel>)

    @Update
    suspend fun update(updateAll: List<WeekCellModel>)

}