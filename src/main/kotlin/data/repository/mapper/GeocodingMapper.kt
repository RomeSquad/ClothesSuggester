package org.example.data.repository.mapper

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import logic.exceptions.DataError
import logic.exceptions.NetworkError
import org.example.data.api.models.GeocodingDto

class GeocodingMapper {
    private val mapper = jacksonObjectMapper()

    fun toCoordinates(jsonString: String, city: String): GeocodingDto {
        val json = try {
            mapper.readTree(jsonString)
        } catch (e: Exception) {
            throw NetworkError("Failed to parse geocoding JSON for city '$city': ${e.message}")
        }
        val results = json.get("results")?.takeIf { it.isArray }
            ?: throw DataError("City '$city' not found or invalid response")
        if (results.size() == 0) {
            throw DataError("City '$city' not found")
        }
        val result = results.get(0)
        return GeocodingDto(
            latitude = result.get("latitude")?.asDouble()
                ?: throw DataError("Missing or invalid 'latitude' for city '$city'"),
            longitude = result.get("longitude")?.asDouble()
                ?: throw DataError("Missing or invalid 'longitude' for city '$city'")
        )
    }
}