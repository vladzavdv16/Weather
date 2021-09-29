package com.light.notes.weather.data.model.week_and_hours

import kotlinx.serialization.Serializable

@Serializable
data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)
