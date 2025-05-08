package org.example.presentation.navigation

import org.example.logic.usecase.GetWeatherUseCase
import org.example.presentation.io.InputReader
import org.example.presentation.io.UiDisplayer
import presentation.action.GetWeatherMenuAction
import kotlin.system.exitProcess

class MainUiController(
    private val navigationController: NavigationController,
    private val viewer: UiDisplayer,
    private val reader: InputReader,
    private val getWeatherUseCase: GetWeatherUseCase
) : NavigationCallBack {
    init {
        navigationController.registerNavigationCallBack(this)
    }

    override fun onNavigate(route: Route) {
        when (route) {
            Route.Home -> TODO()
            Route.ShowWeather -> GetWeatherMenuAction(
                getWeatherUseCase = getWeatherUseCase,
                ui = viewer,
                inputReader = reader,
                navigateToSuggestScreen = { navigationController.navigateTo(Route.SuggestClothes) },
                retryAgain = { navigationController.navigateTo(Route.ShowWeather) },
                navigateBack = { navigationController.popBackStack() }
            )
            Route.SuggestClothes -> TODO()
        }
    }

    override fun onFinish() {
        viewer.displayMessage("Exiting...")
        exitProcess(0)
    }
}