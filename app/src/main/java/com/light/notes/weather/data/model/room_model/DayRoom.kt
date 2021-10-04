package com.light.notes.weather.data.model.room_model

import androidx.room.Entity

@Entity(tableName = "day")
data class DayRoom(
    val name: String,
    val temp: Int,
    val description: String,
    val icon: String
)
