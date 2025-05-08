package org.example.logic.usecase

import logic.entity.Weather
import org.example.logic.repository.WeatherRepository

class GetWeatherUseCase (
    private val weatherRepository: WeatherRepository
) {
    suspend fun getWeather(city: String): Weather {
        return weatherRepository.getWeather(city)
    }
}