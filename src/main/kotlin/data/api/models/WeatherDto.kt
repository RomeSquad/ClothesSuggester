package org.example.data.api.models

data class WeatherDto(
    val temperature: Double,
    val precipitation: Double,
    val windSpeed: Double,
    val humidity: Double,
    val uvIndex: Double?
)
