package com.light.notes.weather.data.model.day

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class MainDay(@Contextual val day: Day)