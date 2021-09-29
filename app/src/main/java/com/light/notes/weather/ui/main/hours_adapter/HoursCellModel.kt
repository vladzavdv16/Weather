package com.light.notes.weather.ui.main.hours_adapter


import com.light.notes.weather.domain.model.HoursModel
import com.light.notes.weather.util.toDateFormatOf

data class HoursCellModel(
    val time: Long,
    val temp: Int,
    val image: String
)

fun HoursModel.mapToUIHours(): HoursCellModel {
    return HoursCellModel(
        time = time,
        temp = temp,
        image = image
    )
}