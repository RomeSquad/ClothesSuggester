package logic.state

import logic.entity.Weather
import org.example.logic.repository.ClothingSuggestion
import org.example.logic.utils.ClothingLists

class ColdWeatherState: ClothingSuggestion {
    override fun condition(weather: Weather): Boolean {
        return weather.temperature in 0.0..10.00
    }

    override fun getClothingLists(): List<String> {
        return ClothingLists.clothingForColdWeather
    }
}