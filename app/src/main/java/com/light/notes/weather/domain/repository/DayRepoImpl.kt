package com.light.notes.weather.domain.repository



import com.light.notes.weather.data.network.Retrofit
import com.light.notes.weather.domain.model.DayModel
import com.light.notes.weather.domain.model.mapToDomain
import com.light.notes.weather.ui.main.MainFragment


class DayRepoImpl : DayRepo {

    override suspend fun fetchDay(): DayModel {
        return Retrofit.instance.dayApi.getAllDay(MainFragment.LAT, MainFragment.LON).body()!!.mapToDomain()


    }
}