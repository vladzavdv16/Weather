package com.light.notes.weather.domain.repository

import com.light.notes.weather.domain.model.HoursModel

interface HoursRepo {

    suspend fun fetchHours(): List<HoursModel>
}