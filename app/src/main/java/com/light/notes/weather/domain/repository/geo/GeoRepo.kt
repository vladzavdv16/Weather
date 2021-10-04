package com.light.notes.weather.domain.repository.geo



interface GeoRepo {

    fun geo(lat: String, lon: String)
}