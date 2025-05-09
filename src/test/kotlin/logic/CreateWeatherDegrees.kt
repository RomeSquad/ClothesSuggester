package logic

import logic.entity.Weather

fun createWeatherDegrees(
    temperature: Double = 0.0,
    precipitation: Double = 0.0,
    windSpeed: Double = 0.0,
    humidity: Double = 0.0,
    uvIndex: Double = 0.0
): Weather {
    return Weather (
        temperature = temperature,
        precipitation = precipitation,
        windSpeed = windSpeed,
        humidity = humidity,
        uvIndex = uvIndex
    )
}