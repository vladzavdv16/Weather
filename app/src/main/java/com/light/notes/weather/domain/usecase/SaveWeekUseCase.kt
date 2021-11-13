package com.light.notes.weather.domain.usecase

import com.light.notes.weather.domain.model.WeekModel
import com.light.notes.weather.domain.repository.WeekRepo

class SaveWeekUseCase(private val weekRepo: WeekRepo) {

    suspend fun execute(dataWeek: List<WeekModel>): Boolean {
        return weekRepo.saveWeek(dataWeek = dataWeek)
    }
}