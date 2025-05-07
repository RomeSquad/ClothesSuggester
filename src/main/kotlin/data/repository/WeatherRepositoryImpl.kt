package org.example.data.repository

import logic.entity.Weather
import logic.exceptions.DataError
import logic.exceptions.NetworkError
import org.example.data.api.GeocodingApi
import org.example.data.api.WeatherApi
import org.example.logic.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val geocodingApi: GeocodingApi
) : WeatherRepository {
    override suspend fun getWeather(city: String): Weather {
        if (city.isBlank()) {
            throw DataError("City name cannot be empty")
        }
        try {
            val coordinates = geocodingApi.fetchCoordinates(city)
            val weatherDto = weatherApi.fetchWeather(coordinates.latitude, coordinates.longitude)
            return Weather(
                temperature = weatherDto.temperature,
                precipitation = weatherDto.precipitation,
                windSpeed = weatherDto.windSpeed,
                humidity = weatherDto.humidity,
                uvIndex = weatherDto.uvIndex ?: 0.0
            )
        } catch (e: Exception) {
            when (e) {
                is NetworkError -> throw NetworkError("Failed to fetch weather for city '$city': ${e.message}")
                is DataError -> throw DataError("Invalid weather data for city '$city': ${e.message}")
                else -> throw DataError("An unexpected error occurred: ${e.message}")
            }
        }
    }
}