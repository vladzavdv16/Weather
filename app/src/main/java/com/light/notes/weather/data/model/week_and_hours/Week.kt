package com.light.notes.weather.data.model.week_and_hours

import kotlinx.serialization.Serializable

@Serializable
data class Week(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val hourly: List<Hourly>,
    val daily: List<Daily>

) {
}