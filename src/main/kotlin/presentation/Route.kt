package presentation

sealed interface Route {
    data object Home : Route
    data object SuggestClothes : Route
    data object ShowWeather : Route
}