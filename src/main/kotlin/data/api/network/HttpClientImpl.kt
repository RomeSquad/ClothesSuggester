package org.example.data.api.network

import logic.exceptions.NetworkError
import okhttp3.OkHttpClient

class HttpClientImpl(
    private val client: OkHttpClient = OkHttpClient()
) : HttpClient {
    override suspend fun fetchUrl(url: String): String {
        val request = okhttp3.Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            throw NetworkError("HTTP request failed: ${response.code}")
        }
        return response.body?.string() ?: throw NetworkError("No response body")
    }
}