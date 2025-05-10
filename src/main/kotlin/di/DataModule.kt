package org.example.di

import org.example.data.api.WeatherApi
import org.example.data.api.WeatherApiImpl
import org.example.data.repository.WeatherRepositoryImpl
import org.example.data.repository.mapper.GeocodingMapper
import org.example.data.repository.mapper.WeatherMapper
import org.example.logic.repository.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
    single { GeocodingMapper() }
    single { WeatherMapper() }

    single<WeatherApi> {
        WeatherApiImpl(
            httpClient = get(),
            urlBuilder = get(),
            config = get(),
            weatherMapper = get(),
            geocodingMapper = get()
        )
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherApi = get(),
            geocodingApi = get()
        )
    }

}