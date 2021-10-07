package com.light.notes.weather.ui.main.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.light.notes.weather.domain.model.DayModel

@Entity(tableName = "table_day")
data class DayCellModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val temp: Int,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val icon: String
)

fun DayModel.mapToUI(): DayCellModel {
    return DayCellModel(
        name = name,
        temp = temp,
        description = description,
        icon = icon
    )
}
