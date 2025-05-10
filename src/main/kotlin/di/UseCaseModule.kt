package org.example.di

import org.example.logic.usecase.GetWeatherUseCase
import org.example.logic.usecase.SuggestClothingUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetWeatherUseCase(
            weatherRepository = get()
        )
    }

    factory { SuggestClothingUseCase(get()) }

}