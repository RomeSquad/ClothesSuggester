package presentation

import org.example.presentation.io.InputReader
import org.example.presentation.io.UiDisplayer
import kotlin.system.exitProcess

class MainUiController(
    private val navigationController: NavigationController,
    private val viewer: UiDisplayer,
    private val reader: InputReader,
) : NavigationCallBack {
    init {
        navigationController.registerNavigationCallBack(this)
    }

    override fun onNavigate(route: Route) {
        when (route) {
            Route.Home -> TODO()
            Route.ShowWeather -> TODO()
            Route.SuggestClothes -> TODO()
        }
    }

    override fun onFinish() {
        viewer.displayMessage("Exiting...")
        exitProcess(0)
    }
}