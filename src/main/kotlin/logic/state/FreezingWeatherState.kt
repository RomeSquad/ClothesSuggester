package logic.state

import logic.entity.Weather
import org.example.logic.repository.ClothingSuggestion
import org.example.logic.utils.ClothingLists

class FreezingWeatherState: ClothingSuggestion {
    override fun condition(weather: Weather): Boolean {
        return weather.temperature < 0
    }

    override fun getClothingLists(): List<String> {
        return ClothingLists.clothingForFreezeWeather
    }
}