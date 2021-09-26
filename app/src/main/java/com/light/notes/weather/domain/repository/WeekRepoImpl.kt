package com.light.notes.weather.domain.repository

import com.light.notes.weather.domain.model.WeekModel
import kotlinx.coroutines.delay

class WeekRepoImpl : WeekRepo {
    override suspend fun fetchWeek(): List<WeekModel> {
        delay(2000)
        return listOf(
            WeekModel(date = "12 апреля", description = "переменная облачность", tempDay = 15, tempNight = 7),
            WeekModel(date = "12 апреля", description = "переменная облачность", tempDay = 15, tempNight = 7),
            WeekModel(date = "12 апреля", description = "переменная облачность", tempDay = 15, tempNight = 7),
            WeekModel(date = "12 апреля", description = "переменная облачность", tempDay = 15, tempNight = 7),
            WeekModel(date = "12 апреля", description = "переменная облачность", tempDay = 15, tempNight = 7),
            WeekModel(date = "12 апреля", description = "переменная облачность", tempDay = 15, tempNight = 7),
            WeekModel(date = "12 апреля", description = "переменная облачность", tempDay = 15, tempNight = 7)
        )
    }
}