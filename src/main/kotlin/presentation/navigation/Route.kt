package org.example.presentation.navigation

sealed class Route(val description: String) {
    data object Home : Route("Home")
    data object SuggestClothes : Route("Suggest Clothes")
    data object ShowWeather : Route("Show Weather")
}