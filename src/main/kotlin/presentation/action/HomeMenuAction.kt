package org.example.presentation.action

import kotlinx.coroutines.runBlocking
import org.example.presentation.io.InputReader
import org.example.presentation.io.UiDisplayer
import org.example.presentation.navigation.Route

class HomeMenuAction(
    private val options: List<Route>,
    private val ui: UiDisplayer,
    private val inputReader: InputReader,
    override val description: String = "Home Screen",
    val navigateToSuggestScreen: () -> Unit = {},
    val navigateToShowWeatherScreen: () -> Unit = {},
    val retryAgain: () -> Unit = {},
    val exit: () -> Unit = {}
) : MenuAction {
    operator fun invoke() {
        runBlocking {
            ui.displayMessage("Hi! Welcome to the Weather App")
            ui.displayMessage("What would you like to do?")
            options.forEachIndexed { index, route ->
                ui.displayMessage("${index + 1}. ${route.description}")
            }
            val option = inputReader.readIntOrNull()

            when (option) {
                1 -> navigateToShowWeatherScreen()
                2 -> navigateToSuggestScreen()
                else -> {
                    ui.displayMessage("option cannot be empty")
                    ui.displayMessage("Do you want to retry? (yes/no)")
                    if (inputReader.readString() in "yes") {
                        retryAgain()
                    } else {
                        exit()
                    }
                }
            }

        }
    }
}