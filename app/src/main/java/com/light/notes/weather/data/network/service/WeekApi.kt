package com.light.notes.weather.data.network.service

import com.light.notes.weather.data.model.week_and_hours.Week

import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface WeekApi {

    @GET("data/2.5/onecall?exclude=current,minutely,alerts&lang=ru&units=metric&lang=ru&appid=603992d4b72cb637ea47e8c8557a30e8")
    suspend fun getAllWeek(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Response<Week>
}