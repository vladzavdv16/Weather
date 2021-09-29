package com.light.notes.weather.data.model.day

import kotlinx.serialization.Serializable

@Serializable
data class Sys(

    val country: String,
    val sunrise: Int,
    val sunset: Int
)