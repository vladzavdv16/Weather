package com.light.notes.weather.data.model.day

import kotlinx.serialization.Serializable

@Serializable
data class Wind(

    val speed: Double,
    val deg: Int,
//    val gust: Double
)