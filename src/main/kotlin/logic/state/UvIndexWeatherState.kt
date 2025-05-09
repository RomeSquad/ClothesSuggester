package logic.state

import logic.entity.Weather
import org.example.logic.repository.ClothingSuggestion
import org.example.logic.utils.ClothingLists

class UvIndexWeatherState: ClothingSuggestion {
    override fun condition(weather: Weather): Boolean {
        return weather.uvIndex > 7
    }

    override fun getClothingLists(): List<String> {
        return ClothingLists.clothingForUvIndexWeather
    }
}