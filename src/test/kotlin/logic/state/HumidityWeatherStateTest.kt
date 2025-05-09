package logic.state

import logic.createWeatherDegrees
import org.example.logic.utils.ClothingLists
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class HumidityWeatherStateTest {

    private val humidityWeatherState = HumidityWeatherState()

    @Test
    fun `should condition return true when humidity more than 70 and temperature more than 25`() {
        val weather = createWeatherDegrees(humidity = 75.0, temperature = 30.0)

        val result = humidityWeatherState.condition(weather)

        assertTrue(result)
    }

    @Test
    fun `should condition return false when humidity more than 70 but temperature less than 25`() {
        val weather = createWeatherDegrees(humidity = 75.0, temperature = 20.0)

        val result = humidityWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should condition return false when humidity less than 70 and temperature more than 25`() {
        val weather = createWeatherDegrees(humidity = 60.0, temperature = 30.0)

        val result = humidityWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should getClothingLists return humidity clothes when weather is humidity`() {
        val humidityClothes = ClothingLists.clothingForHumidityWeather

        val result = humidityWeatherState.getClothingLists()

        assertEquals(humidityClothes, result)
    }
}