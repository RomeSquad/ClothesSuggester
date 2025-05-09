package org.example.logic.usecase

import logic.entity.Weather
import org.example.logic.repository.ClothingSuggestion
import logic.state.ColdWeatherState
import logic.state.FreezingWeatherState
import logic.state.HotWeatherState
import logic.state.HumidityWeatherState
import logic.state.RainyWeatherState
import logic.state.UvIndexWeatherState
import logic.state.WarmWeatherState

class SuggestClothingUseCase (
    private val rules: List<ClothingSuggestion> = listOf(
        ColdWeatherState(),
        FreezingWeatherState(),
        HotWeatherState(),
        HumidityWeatherState(),
        RainyWeatherState(),
        UvIndexWeatherState(),
        WarmWeatherState()
    )
) {
    fun suggestClothing(weather: Weather): List<String> {
        return rules
            .filter { it.condition(weather) }
            .flatMap { it.getClothingLists() }
    }
}