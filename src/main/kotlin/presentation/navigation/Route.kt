package org.example.presentation.navigation

import logic.entity.Weather

sealed class Route(val description: String) {
    data object Home : Route("Home")
    data class SuggestClothes(val weather: Weather? = null) : Route("Suggest Clothes")
    data object ShowWeather : Route("Show Weather")
}