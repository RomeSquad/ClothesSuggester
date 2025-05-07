package org.example.data.repository.mapper

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import logic.exceptions.DataError
import logic.exceptions.NetworkError
import org.example.data.api.models.WeatherDto

class WeatherMapper {
    private val mapper = jacksonObjectMapper()

    fun toWeather(jsonString: String): WeatherDto {
        val json = try {
            mapper.readTree(jsonString)
        } catch (e: Exception) {
            throw NetworkError("Failed to parse weather JSON: ${e.message}")
        }
        val current = json.get("current")
            ?: throw DataError("Weather data unavailable: 'current' field missing")
        return WeatherDto(
            temperature = current.get("temperature_2m")?.asDouble()
                ?: throw DataError("Missing or invalid 'temperature_2m'"),
            precipitation = current.get("precipitation")?.asDouble()
                ?: throw DataError("Missing or invalid 'precipitation'"),
            windSpeed = current.get("wind_speed_10m")?.asDouble()
                ?: throw DataError("Missing or invalid 'wind_speed_10m'"),
            humidity = current.get("relative_humidity_2m")?.asDouble()
                ?: throw DataError("Missing or invalid 'relative_humidity_2m'"),
            uvIndex = current["uv_index"]?.asDouble() ?: 0.0 // Nullable, defaults to 0.0 in WeatherDto
        )
    }
}