package com.light.notes.weather.ui.main.week_adapter

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.light.notes.weather.domain.model.WeekModel

@Entity(tableName = "table_week")
data class WeekCellModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val date: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val tempDay: Int,
    @ColumnInfo
    val tempNight: Int,
    @ColumnInfo
    val image: String
)

fun WeekModel.mapToUI(): WeekCellModel {
    return WeekCellModel(date = date, description = description, tempDay = tempDay, tempNight = tempNight, image = image)
}

//fun ModelWeek.mapToRoom(): WeekCellModel{
//    return WeekCellModel(date = date, description = description, tempDay = tempDay, tempNight = tempNight, image = image)
//}