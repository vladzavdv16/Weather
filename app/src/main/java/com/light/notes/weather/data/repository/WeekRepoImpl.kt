package com.light.notes.weather.data.repository

import com.light.notes.weather.data.network.Retrofit
import com.light.notes.weather.domain.model.WeekModel
import com.light.notes.weather.domain.model.mapToDomain
import com.light.notes.weather.domain.repository.WeekRepo
import com.light.notes.weather.ui.main.MainFragment

class WeekRepoImpl : WeekRepo {

    override suspend fun fetchWeek(): List<WeekModel> {
        return Retrofit.instance.weekApi.getAllWeek(MainFragment.LAT, MainFragment.LON).body()!!.daily.map {
           it.mapToDomain()
        }
    }

    override suspend fun saveWeek(dataWeek: List<WeekModel>): Boolean {
        TODO("Not yet implemented")
    }

}