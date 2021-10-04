package com.light.notes.weather.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_week")
data class ModelWeek(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
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