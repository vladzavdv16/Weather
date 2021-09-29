package com.light.notes.weather.domain.model


import com.light.notes.weather.data.model.week_and_hours.Daily

data class WeekModel(
    val date: String,
    val description: String,
    val tempDay: Int,
    val tempNight: Int,
    val image: String
)

fun Daily.mapToDomain(): WeekModel {
    return WeekModel(
        date = this.dt.toString(),
        description = this.weather[0].description,
        tempNight = this.temp.night.toInt(),
        tempDay = this.temp.day.toInt(),
        image = this.weather[0].icon
    )
}