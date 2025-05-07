package org.example.data.api

import org.example.data.api.models.GeocodingDto

interface GeocodingApi {
    suspend fun fetchCoordinates(city: String): GeocodingDto
}