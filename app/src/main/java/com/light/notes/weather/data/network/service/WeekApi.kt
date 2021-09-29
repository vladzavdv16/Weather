package com.light.notes.weather.data.network.service

import com.light.notes.weather.data.model.week_and_hours.Week

import retrofit2.Response

import retrofit2.http.GET

interface WeekApi {

    @GET("data/2.5/onecall?lon=45.1749&lat=54.1838&exclude=current,minutely,alerts&lang=ru&units=metric&lang=ru&appid=603992d4b72cb637ea47e8c8557a30e8")
    suspend fun getAllWeek(): Response<Week>
}