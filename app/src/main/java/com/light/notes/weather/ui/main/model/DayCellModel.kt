package com.light.notes.weather.ui.main.model


import com.light.notes.weather.domain.model.DayModel

data class DayCellModel(
    val name: String,
    val temp: Int,
    val description: String,
    val icon: String
)

fun DayModel.mapToUI(): DayCellModel {
    return DayCellModel(
        name = name,
        temp = temp,
        description = description,
        icon = icon
    )
}
