package com.light.notes.weather.data.model.week_and_hours

import kotlinx.serialization.Serializable

@Serializable
data class Feels_like(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)
