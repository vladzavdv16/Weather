package com.light.notes.weather.data.model.week_and_hours

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val moonrise: Int,
    val moonset: Int,
    val moon_phase: Double,
    @Contextual val temp: Temp,
    @Contextual val feels_like: Feels_like,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val wind_speed: Double,
    val wind_deg: Int,
    val wind_gust: Double,
    val weather: List<Weather>,
    val clouds: Int,
    val pop: Double,
    val rain: Double = 0.0,
    val uvi: Double = 0.0
)
