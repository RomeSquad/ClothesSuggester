package org.example.data.api

import org.example.data.api.models.WeatherDto

interface WeatherApi {
    suspend fun fetchWeather(latitude: Double, longitude: Double): WeatherDto
}