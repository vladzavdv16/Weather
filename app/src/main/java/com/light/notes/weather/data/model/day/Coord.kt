package com.light.notes.weather.data.model.day

import kotlinx.serialization.Serializable

@Serializable
data class Coord(

    val lon: Double,
    val lat: Double
)