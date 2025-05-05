package logic.entity


data class WeatherWithUv(
    val temperature: Double,
    val precipitation: Double,
    val windSpeed: Double,
    val humidity: Double,
    val uvIndex: Double
)
