package org.example.presentation.navigation

sealed interface Route {
    data object Home : Route
    data object SuggestClothes : Route
    data object ShowWeather : Route
}