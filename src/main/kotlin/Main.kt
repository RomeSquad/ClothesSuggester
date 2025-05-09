package org.example

import org.example.data.api.WeatherApiImpl
import org.example.data.api.network.HttpClientImpl
import org.example.data.api.network.NetworkConfig
import org.example.data.api.network.UrlBuilder
import org.example.data.repository.WeatherRepositoryImpl
import org.example.data.repository.mapper.GeocodingMapper
import org.example.data.repository.mapper.WeatherMapper
import org.example.logic.usecase.GetWeatherUseCase
import org.example.presentation.io.ConsoleInputReader
import org.example.presentation.io.ConsoleWriter
import org.example.presentation.navigation.MainUiController
import org.example.presentation.navigation.NavigationController
import org.example.presentation.navigation.Route


fun main() {
    MainUiController(
        navigationController = NavigationController(),
        viewer = ConsoleWriter(),
        reader = ConsoleInputReader(),
        getWeatherUseCase = GetWeatherUseCase(
            weatherRepository = WeatherRepositoryImpl(
                weatherApi = WeatherApiImpl(
                    httpClient = HttpClientImpl(),
                    urlBuilder = UrlBuilder(),
                    config = NetworkConfig,
                    weatherMapper = WeatherMapper(),
                    geocodingMapper = GeocodingMapper()
                ),
                geocodingApi = WeatherApiImpl(
                    httpClient = HttpClientImpl(),
                    urlBuilder = UrlBuilder(),
                    config = NetworkConfig,
                    weatherMapper = WeatherMapper(),
                    geocodingMapper = GeocodingMapper()
                )
            )
        )
    )

}