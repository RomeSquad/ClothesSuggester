package org.example.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import logic.exceptions.NetworkError
import org.example.data.api.models.GeocodingDto
import org.example.data.api.models.WeatherDto
import org.example.data.api.network.HttpClient
import org.example.data.api.network.NetworkConfig
import org.example.data.api.network.UrlBuilder
import org.example.data.repository.mapper.GeocodingMapper
import org.example.data.repository.mapper.WeatherMapper
import kotlin.math.pow
import kotlin.time.Duration.Companion.seconds

class WeatherApiImpl(
    private val httpClient: HttpClient,
    private val urlBuilder: UrlBuilder,
    private val config: NetworkConfig,
    private val weatherMapper: WeatherMapper,
    private val geocodingMapper: GeocodingMapper,
) : WeatherApi, GeocodingApi {

    override suspend fun fetchWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDto = withContext(Dispatchers.IO) {
        val weatherJson = fetchWithRetry(
            url = urlBuilder.buildWeatherUrl(latitude, longitude),
            operation = "weather"
        )
        try {
            weatherMapper.toWeather(weatherJson)
        } catch (e: Exception) {
            throw NetworkError("Failed to parse weather data: ${e.message}")
        }
    }

    override suspend fun fetchCoordinates(city: String): GeocodingDto = withContext(Dispatchers.IO) {
        val geocodingJson = fetchWithRetry(
            url = urlBuilder.buildGeocodingUrl(city),
            operation = "geocoding"
        )
        try {
            geocodingMapper.toCoordinates(geocodingJson, city)
        } catch (e: Exception) {
            throw NetworkError("Failed to parse geocoding data: ${e.message}")
        }

    }

    private suspend fun fetchWithRetry(url: String, operation: String, attempt: Int = 0): String {
        return try {
            httpClient.fetchUrl(url)
        } catch (e: Exception) {
            if (attempt < config.MAX_RETRIES) {
                val delaySeconds = config.RETRY_DELAY * 2.0.pow(attempt)
                delay(delaySeconds.seconds)
                fetchWithRetry(url, operation, attempt + 1)
            } else {
                throw NetworkError("Failed to fetch $operation data after ${config.MAX_RETRIES + 1} attempts")
            }
        }
    }
}