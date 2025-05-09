package org.example.presentation.navigation

import logic.entity.Weather
import org.example.logic.usecase.GetWeatherUseCase
import org.example.logic.usecase.SuggestClothingUseCase
import org.example.presentation.action.GetClothesSuggestionsMenuAction
import org.example.presentation.action.HomeMenuAction
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
        val weather: Weather? = null
        when (route) {
            is Route.Home -> HomeMenuAction(
                options = listOf(Route.ShowWeather, Route.SuggestClothes(null)),
                ui = viewer,
                inputReader = reader,
                navigateToSuggestScreen = { navigationController.navigateTo(Route.SuggestClothes(null)) },
                navigateToShowWeatherScreen = { navigationController.navigateTo(Route.ShowWeather) },
                retryAgain = { navigationController.navigateTo(Route.Home, false) },
                exit = { onFinish() }
            ).invoke()

            is Route.ShowWeather -> GetWeatherMenuAction(
                getWeatherUseCase = getWeatherUseCase,
                ui = viewer,
                inputReader = reader,
                navigateToSuggestScreen = { weather ->
                    navigationController.navigateTo(Route.SuggestClothes(weather))
                },
                retryAgain = { navigationController.navigateTo(Route.ShowWeather,false) },
                navigateBack = { navigationController.popBackStack() }
            ).invoke()

            is Route.SuggestClothes -> GetClothesSuggestionsMenuAction(
                weather = route.weather,
                suggestClothingUseCase = SuggestClothingUseCase(),
                getWeatherUseCase = getWeatherUseCase,
                ui = viewer,
                inputReader = reader,
                retryAgain = { navigationController.navigateTo(Route.SuggestClothes(route.weather),false) },
                navigateBack = { navigationController.popBackStack() }
            ).invoke()
        }
    }

    override fun onFinish() {
        viewer.displayMessage("Exiting...")
        exitProcess(0)
    }
}