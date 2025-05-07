package org.example.data.api.network

class UrlBuilder {
    fun buildWeatherUrl(latitude: Double, longitude: Double): String {
        return "${NetworkConfig.WEATHER_API_BASE_URL}?latitude=$latitude&longitude=$longitude&${NetworkConfig.WEATHER_PARAMS}"
    }

    fun buildGeocodingUrl(city: String): String {
        return "${NetworkConfig.GEOCODING_API_BASE_URL}?name=$city&${NetworkConfig.GEOCODING_PARAMS}"
    }
}