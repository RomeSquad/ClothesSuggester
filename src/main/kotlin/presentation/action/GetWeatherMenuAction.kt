package presentation.action

import kotlinx.coroutines.runBlocking
import logic.entity.Weather
import org.example.logic.usecase.GetWeatherUseCase
import org.example.presentation.action.MenuAction
import org.example.presentation.io.InputReader
import org.example.presentation.io.UiDisplayer

class GetWeatherMenuAction(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val ui: UiDisplayer,
    private val inputReader: InputReader,
    override val description: String = "Get Weather",
    private val navigateToSuggestScreen: (Weather) -> Unit = {},
    private val retryAgain: () -> Unit = {},
    private val navigateBack: () -> Unit = {}
) : MenuAction {

    operator fun invoke() = runBlocking {
        getWeatherForCity()
    }

    private suspend fun getWeatherForCity() {
        ui.displayMessage("Enter your City:")
        val city = inputReader.readString()

        if (city.isBlank()) {
            handleEmptyCity()
            return
        }

        runCatching {
            val weatherResult = getWeatherUseCase.getWeather(city)
            displayWeatherInfo(city, weatherResult)
            promptForNextAction(weatherResult)
        }.onFailure { error ->
            handleError(error)
        }
    }

    private fun handleEmptyCity() {
        ui.displayMessage("City cannot be empty")
        promptForRetry()
    }

    private fun displayWeatherInfo(city: String, weather: Weather) {
        ui.displayMessage("The current weather in $city")
        ui.displayMessage("Temperature: ${weather.temperature}°C")
        ui.displayMessage("Humidity: ${weather.humidity}%")
        ui.displayMessage("Wind Speed: ${weather.windSpeed} m/s")
        ui.displayMessage("======================================")
    }

    private fun promptForNextAction(weather: Weather) {
        ui.displayMessage("What would you like to do next?")
        ui.displayMessage("1. Get clothing suggestions")
        ui.displayMessage("2. Check another city")
        ui.displayMessage("3. Go back to main menu")

        when (inputReader.readString()) {
            "1" -> navigateToSuggestScreen(weather)
            "2" -> retryAgain()
            else -> navigateBack()
        }
    }

    private fun handleError(error: Throwable) {
        ui.displayMessage("Failed to get result for your input: ${error.message}")
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