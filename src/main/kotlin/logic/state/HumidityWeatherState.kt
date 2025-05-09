package logic.state

import logic.entity.Weather
import org.example.logic.repository.ClothingSuggestion
import org.example.logic.utils.ClothingLists

class HumidityWeatherState: ClothingSuggestion {
    override fun condition(weather: Weather): Boolean {
        return weather.humidity > 70 && weather.temperature > 25
    }

    override fun getClothingLists(): List<String> {
        return ClothingLists.clothingForHumidityWeather
    }
}