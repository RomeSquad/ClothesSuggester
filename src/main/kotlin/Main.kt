package org.example

import kotlinx.coroutines.runBlocking
import org.example.di.koinModule
import org.example.presentation.navigation.MainUiController
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin


fun main() {
    startKoin { modules(koinModule) }

    val app: MainUiController = getKoin().get()
    runBlocking {
        app
    }
/*
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
*/

}