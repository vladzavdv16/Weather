package com.light.notes.weather.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.light.notes.weather.data.network.service.DayApi
import com.light.notes.weather.data.network.service.WeekApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    companion object {
        val instance = Retrofit()
    }

    //
    private fun okHttpInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(okHttpInterceptor()).build()

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val weekApi: WeekApi = retrofitClient.create(WeekApi::class.java)
    val dayApi: DayApi = retrofitClient.create(DayApi::class.java)

}