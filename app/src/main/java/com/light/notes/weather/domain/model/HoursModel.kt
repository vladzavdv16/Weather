package com.light.notes.weather.domain.model

import com.light.notes.weather.data.model.week_and_hours.Hourly

data class HoursModel(
    val time: Long,
    val temp: Int,
    val image: String
)

fun Hourly.mapToDomainHours(): HoursModel {
    return HoursModel(
        time = this.dt,
        temp = this.temp.toInt(),
        image = this.weather[0].icon
    )
}