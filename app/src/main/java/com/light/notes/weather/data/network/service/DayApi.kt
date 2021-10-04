package com.light.notes.weather.data.network.service

import com.light.notes.weather.data.model.day.Day
import com.light.notes.weather.data.model.day.MainDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DayApi {

    @GET("data/2.5/weather?lang=ru&units=metric&appid=603992d4b72cb637ea47e8c8557a30e8")
    suspend fun getAllDay(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Response<Day>
}