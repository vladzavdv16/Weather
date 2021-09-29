package com.light.notes.weather.domain.repository

import com.light.notes.weather.data.model.day.Day
import com.light.notes.weather.data.network.Retrofit
import com.light.notes.weather.domain.model.DayModel
import com.light.notes.weather.domain.model.mapToDomain

class DayRepoImpl : DayRepo {

    override suspend fun fetchDay() :DayModel{
        return Retrofit.instance.dayApi.getAllDay().body()!!.mapToDomain()
    }
}