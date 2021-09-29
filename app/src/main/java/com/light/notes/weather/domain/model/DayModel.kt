package com.light.notes.weather.domain.model

import com.light.notes.weather.data.model.day.Day


data class DayModel(
    val name: String,
    val temp: Int,
    val description: String,
    val icon: String
)

fun Day.mapToDomain(): DayModel {
    return DayModel(
        name = this.name,
        temp = this.main.temp.toInt(),
        description = this.weather[0].description,
        icon = this.weather[0].icon
    )
}

