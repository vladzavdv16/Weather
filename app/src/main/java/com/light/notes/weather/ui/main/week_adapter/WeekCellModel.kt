package com.light.notes.weather.ui.main.week_adapter

import com.light.notes.weather.domain.model.WeekModel

data class WeekCellModel(
    val date: String,
    val description: String,
    val tempDay: Int,
    val tempNight: Int,
    val image: String
)

fun WeekModel.mapToUI(): WeekCellModel {
    return WeekCellModel(date = date, description = description, tempDay = tempDay, tempNight = tempNight, image = image)
}