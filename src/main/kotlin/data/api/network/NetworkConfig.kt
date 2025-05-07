package org.example.data.api.network

data class NetworkConfig(
    val weatherBaseUrl: String = "https://api.open-meteo.com/v1/forecast",
    val geocodingBaseUrl: String = "https://geocoding-api.open-meteo.com/v1/search",
    val weatherParams: String = "current=temperature_2m,precipitation,wind_speed_10m,relative_humidity_2m",
    val geocodingParams: String = "count=1"
)
