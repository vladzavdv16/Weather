package com.light.notes.weather.domain.repository

import com.light.notes.weather.domain.model.HoursModel
import kotlinx.coroutines.delay

class HoursRepoImpl : HoursRepo {
    override suspend fun fetchHours(): List<HoursModel> {
        delay(2000)
        return listOf<HoursModel>(
            HoursModel(time = "21:00", 12),
            HoursModel(time = "21:00", 12),
            HoursModel(time = "21:00", 12),
            HoursModel(time = "21:00", 12),
            HoursModel(time = "21:00", 12),
            HoursModel(time = "21:00", 12),
            HoursModel(time = "21:00", 12),
        )
    }
}