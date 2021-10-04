package com.light.notes.weather.ui.main.hours_adapter


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.light.notes.weather.domain.model.HoursModel

@Entity(tableName = "table_hours")
data class HoursCellModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val time: Long,
    @ColumnInfo
    val temp: Int,
    @ColumnInfo
    val image: String
)

fun HoursModel.mapToUIHours(): HoursCellModel {
    return HoursCellModel(
        time = time,
        temp = temp,
        image = image
    )
}