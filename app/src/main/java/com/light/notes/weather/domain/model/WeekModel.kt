package com.light.notes.weather.domain.model

data class WeekModel(
    val date: String,
    val description: String,
    val tempDay: Int,
    val tempNight: Int
) {
}