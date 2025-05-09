package org.example.presentation.action

import kotlinx.coroutines.runBlocking
import logic.entity.Weather
import org.example.logic.usecase.GetWeatherUseCase
import org.example.logic.usecase.SuggestClothingUseCase
import org.example.presentation.io.InputReader
import org.example.presentation.io.UiDisplayer

class GetClothesSuggestionsMenuAction(
    private var weather: Weather?,
    private val suggestClothingUseCase: SuggestClothingUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val ui: UiDisplayer,
    private val inputReader: InputReader,
    override val description: String = "Clothes Suggestions",
    private val retryAgain: () -> Unit = {},
    private val navigateBack: () -> Unit = {}
) : MenuAction {

    operator fun invoke() = runBlocking {
        ui.displayMessage("Getting clothes suggestions for today")

        when {
            weather != null -> displaySuggestionsForExistingWeather()
            else -> processNewWeatherRequest()
        }
    }

    private suspend fun displaySuggestionsForExistingWeather() {
        try {
            ui.displayMessage("The suggested clothes for today are:")
            displayClothingSuggestions(weather!!)
            promptForRetry()
        } catch (e: Exception) {
            handleError("Failed to get result: ${e.message}")
        }
    }

    private suspend fun processNewWeatherRequest() {
        ui.displayMessage("Enter your City:")
        val city = inputReader.readString()

        if (city.isBlank()) {
            ui.displayMessage("City cannot be empty")
            promptForRetry()
            return
        }

        try {
            val result = getWeatherUseCase.getWeather(city)
            weather = result

            displayWeatherInfo(city, result)
            displayClothingSuggestions(result)
            promptForRetry()
        } catch (e: Exception) {
            handleError("Failed to get result for your input: ${e.message}")
        }
    }

    private fun displayWeatherInfo(city: String, weather: Weather) {
        ui.displayMessage("The current weather in $city")
        ui.displayMessage("Temperature: ${weather.temperature}°C")
        ui.displayMessage("Humidity: ${weather.humidity}%")
        ui.displayMessage("Wind Speed: ${weather.windSpeed} m/s")
        ui.displayMessage("======================================")
    }

    private  fun displayClothingSuggestions(weather: Weather) {
        val suggestions = suggestClothingUseCase.suggestClothing(weather)
        suggestions.forEach { ui.displayMessage(it) }
        ui.displayMessage("======================================")
    }

    private fun handleError(message: String) {
        ui.displayMessage(message)
        promptForRetry()
    }

    private fun promptForRetry() {
        ui.displayMessage("Do you want to retry? (yes/no)")
        if (inputReader.readString().equals("yes", ignoreCase = true)) {
            retryAgain()
        } else {
            navigateBack()
        }
    }
}