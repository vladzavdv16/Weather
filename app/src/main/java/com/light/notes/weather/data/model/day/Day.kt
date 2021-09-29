package com.light.notes.weather.data.model.day

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Day(
    @Contextual val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    @Contextual val main: Main,
    val visibility: Int,
    @Contextual val wind: Wind,
    @Contextual val clouds: Clouds,
    val dt: Int,
    @Contextual val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

