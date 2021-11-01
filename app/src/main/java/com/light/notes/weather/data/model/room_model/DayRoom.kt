package com.light.notes.weather.data.model.room_model

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Entity

@Entity(tableName = "day")
data class DayRoom(
    val temp: Int,
    val description: String,
    val icon: String
)
