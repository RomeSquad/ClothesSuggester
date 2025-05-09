package logic.state

import logic.entity.Weather
import org.example.logic.repository.ClothingSuggestion
import org.example.logic.utils.ClothingLists

class RainyWeatherState: ClothingSuggestion {
    override fun condition(weather: Weather): Boolean {
        return weather.precipitation > 0.5
    }

    override fun getClothingLists(): List<String> {
        return ClothingLists.clothingForRainyWeather
    }
}