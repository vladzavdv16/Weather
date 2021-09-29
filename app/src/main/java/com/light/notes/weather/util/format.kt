package com.light.notes.weather.util

import com.light.notes.weather.ui.main.week_adapter.WeekCellModel
import java.text.SimpleDateFormat
import java.util.*


fun formatDate(list: List<WeekCellModel>, position: Int) {
    SimpleDateFormat.getDateInstance().format(Date((list[position].date).toLong() * 1000L))
}

fun Long.toDateFormatOf(format: String): String {
    val cal = Calendar.getInstance()
    val timeZone = cal.timeZone
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = timeZone
    return sdf.format(Date(this * 1000))
}