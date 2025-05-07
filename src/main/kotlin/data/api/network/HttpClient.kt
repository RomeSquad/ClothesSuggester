package org.example.data.api.network

interface HttpClient {
    suspend fun fetchUrl(url: String): String
}