package org.example.logic.repository

import logic.entity.Weather

interface WeatherRepository {
    suspend fun getWeather(city: String): Weather
}