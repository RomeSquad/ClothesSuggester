package org.example.di

import org.example.presentation.action.GetClothesSuggestionsMenuAction
import org.example.presentation.action.HomeMenuAction
import org.example.presentation.action.MenuAction
import org.example.presentation.io.ConsoleInputReader
import org.example.presentation.io.ConsoleWriter
import org.example.presentation.io.InputReader
import org.example.presentation.io.UiDisplayer
import org.example.presentation.navigation.MainUiController
import org.example.presentation.navigation.NavigationController
import org.koin.dsl.module
import presentation.action.GetWeatherMenuAction

val presentationModule = module {
    single<InputReader> { ConsoleInputReader() }
    single<UiDisplayer> { ConsoleWriter() }

    //single <NavigationCallBack>{ MainUiController(get(),get(),get(),get()) }
    single {  }
    single {
        NavigationController(
            startDestination = get()
        )
    }

    single {
        MainUiController(
            navigationController = get(),
            viewer = get(),
            reader = get(),
            getWeatherUseCase = get()
        )
    }
    single <MenuAction>{ HomeMenuAction(get(),get(),get()) }

    single {
        listOf<MenuAction>(
            GetClothesSuggestionsMenuAction(
                weather = get(),
                suggestClothingUseCase = get(),
                getWeatherUseCase = get(),
                ui = get(),
                inputReader = get(),
                description = get(),
                retryAgain = get(),
                navigateBack = get (),
            ),

            GetWeatherMenuAction(
                getWeatherUseCase = get(),
                ui = get(),
                inputReader = get(),
                description = get(),
                navigateToSuggestScreen = get(),
                retryAgain = get(),
                navigateBack = get()
            ),

            HomeMenuAction(
                navigateToSuggestScreen = get(),
                navigateToShowWeatherScreen = get(),
                retryAgain = get(),
                exit = get(),
                options = get(),
                ui = get(),
                inputReader = get()
            )
        )
    }
}