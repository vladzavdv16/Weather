package com.light.notes.weather.domain.repository

import com.light.notes.weather.data.network.Retrofit
import com.light.notes.weather.domain.model.WeekModel
import com.light.notes.weather.domain.model.mapToDomain
import com.light.notes.weather.ui.main.week_adapter.mapToUI
import kotlinx.coroutines.delay

class WeekRepoImpl : WeekRepo {
    override suspend fun fetchWeek(): List<WeekModel> {
        return Retrofit.instance.weekApi.getAllWeek().body()!!.daily.map {
           it.mapToDomain()
        }
    }
}