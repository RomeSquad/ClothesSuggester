package logic.state

import logic.entity.Weather
import org.example.logic.repository.ClothingSuggestion
import org.example.logic.utils.ClothingLists

class WarmWeatherState: ClothingSuggestion {
    override fun condition(weather: Weather): Boolean {
        return weather.temperature in 10.0..25.0
    }

    override fun getClothingLists(): List<String> {
        return ClothingLists.clothingForWarmWeather
    }
}