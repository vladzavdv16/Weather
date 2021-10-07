package com.light.notes.weather.util

import com.light.notes.weather.ui.MainActivity
import com.light.notes.weather.data.database.day.DayDatabaseRepository
import com.light.notes.weather.data.database.hours.HoursDatabaseRepository
import com.light.notes.weather.data.database.week.WeekDatabaseRepository


lateinit var APP_ACTIVITY: MainActivity

lateinit var WEEKREPOSITORY: WeekDatabaseRepository
lateinit var HOURSREPOSITORY: HoursDatabaseRepository
lateinit var DAYREPOSITORY: DayDatabaseRepository

const val HOUR_DOUBLE_DOT_MINUTE = "HH:mm"

//fun ModelWeek.mapToRoom(): List<WeekCellModel> {
//    return listOf(
//        WeekCellModel(
//            date = date,
//            description = description,
//            tempDay = tempDay,
//            tempNight = tempNight,
//            image = image
//        )
//    )
//}
