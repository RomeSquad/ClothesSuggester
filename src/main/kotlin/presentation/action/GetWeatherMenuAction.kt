package presentation.action

import kotlinx.coroutines.runBlocking
import org.example.logic.usecase.GetWeatherUseCase
import org.example.presentation.action.MenuAction
import org.example.presentation.io.InputReader
import org.example.presentation.io.UiDisplayer

class GetWeatherMenuAction(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val ui: UiDisplayer,
    private val inputReader: InputReader,
    override val description: String = "Get Weather",
    val navigateToSuggestScreen: () -> Unit = {},
    val retryAgain: () -> Unit = {},
    val navigateBack: () -> Unit = {}
) : MenuAction {

    operator fun invoke() {
        runBlocking {
            ui.displayMessage("Enter your City:")
            val city = inputReader.readString()
            if (city.isBlank()) {
                ui.displayMessage("City cannot be empty")
                ui.displayMessage("Do you want to retry? (yes/no)")
                if (inputReader.readString() in "yes") {
                    retryAgain()
                } else {
                    navigateBack()
                }
            }
            runCatching {
                val result = getWeatherUseCase.getWeather(city)

                ui.displayMessage("The current weather in $city")
                ui.displayMessage("Temperature: ${result.temperature}°C")
                ui.displayMessage("Humidity: ${result.humidity}%")
                ui.displayMessage("Wind Speed: ${result.windSpeed} m/s")
                ui.displayMessage("======================================")
                ui.displayMessage("Do you want to retry? (yes/no)")
                if (inputReader.readString() in "yes") {
                    retryAgain()
                } else {
                    navigateBack()
                }
            }.onFailure {
                ui.displayMessage("Failed To get result for your input : ${it.message}")
                ui.displayMessage("Do you want to retry? (yes/no)")
                if (inputReader.readString() in "yes") {
                    retryAgain()
                } else {
                    navigateBack()
                }
            }
        }
    }
}




