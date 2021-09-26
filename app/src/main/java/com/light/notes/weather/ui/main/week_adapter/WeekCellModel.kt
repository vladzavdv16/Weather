package com.light.notes.weather.ui.main.week_adapter

data class WeekCellModel(
    val date: String,
    val description: String,
    val tempDay: Int,
    val tempNight: Int
) {
}