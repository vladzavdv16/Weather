package com.light.notes.weather.data.model.week_and_hours

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val id: Int,
    var main: String,
    var description: String,
    val icon: String
) {

}
