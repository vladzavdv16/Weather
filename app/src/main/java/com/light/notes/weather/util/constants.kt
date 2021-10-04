package com.light.notes.weather.util

import com.light.notes.weather.MainActivity
import com.light.notes.weather.data.database.week.WeekDatabaseRepository
import com.light.notes.weather.data.database.model.ModelWeek
import com.light.notes.weather.ui.main.week_adapter.WeekCellModel


lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: WeekDatabaseRepository
const val HOUR_DOUBLE_DOT_MINUTE = "HH:mm"

fun ModelWeek.mapToRoom(): List<WeekCellModel>{
    return listOf(WeekCellModel(date = date, description = description, tempDay = tempDay, tempNight = tempNight, image = image))
}
