package org.example.data.api.network

object NetworkConfig {
    const val WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/forecast"
    const val GEOCODING_API_BASE_URL = "https://geocoding-api.open-meteo.com/v1/search"
    const val WEATHER_PARAMS = "current=temperature_2m,precipitation,wind_speed_10m,relative_humidity_2m"
    const val GEOCODING_PARAMS = "count=1"
    const val MAX_RETRIES = 5
    const val RETRY_DELAY = 1000L
}
