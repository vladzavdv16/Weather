package com.light.notes.weather.domain.repository.geo

interface GeoRepo {

    suspend fun location(lat: String, lon: String)
}