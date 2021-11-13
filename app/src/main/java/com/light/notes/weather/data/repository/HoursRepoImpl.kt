package com.light.notes.weather.data.repository


import com.light.notes.weather.data.network.Retrofit
import com.light.notes.weather.domain.model.HoursModel
import com.light.notes.weather.domain.model.mapToDomainHours
import com.light.notes.weather.domain.repository.HoursRepo
import com.light.notes.weather.ui.main.MainFragment

class HoursRepoImpl : HoursRepo {
    override suspend fun fetchHours(): List<HoursModel> {
        return Retrofit.instance.weekApi.getAllWeek(MainFragment.LAT, MainFragment.LON)
            .body()!!.hourly.map {
            it.mapToDomainHours()
        }
    }
}