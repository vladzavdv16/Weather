package com.light.notes.weather.domain.repository


import com.light.notes.weather.domain.model.DayModel


interface DayRepo {

    suspend fun fetchDay() : DayModel
}