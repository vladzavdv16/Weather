package com.light.notes.weather.domain.repository


import com.light.notes.weather.data.network.Retrofit
import com.light.notes.weather.domain.model.HoursModel
import com.light.notes.weather.domain.model.mapToDomainHours

class HoursRepoImpl : HoursRepo {
    override suspend fun fetchHours(): List<HoursModel> {
        return Retrofit.instance.weekApi.getAllWeek().body()!!.hourly.map {
            it.mapToDomainHours()
        }
    }
}