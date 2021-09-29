package com.light.notes.weather.domain.repository


import com.light.notes.weather.domain.model.WeekModel

interface WeekRepo {

    suspend fun fetchWeek(): List<WeekModel>
}