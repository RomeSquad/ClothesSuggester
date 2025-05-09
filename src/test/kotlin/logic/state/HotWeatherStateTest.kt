package logic.state

import logic.createWeatherDegrees
import org.example.logic.utils.ClothingLists
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class HotWeatherStateTest {

    private val hotWeatherState = HotWeatherState()

    @Test
    fun `should condition return true when temperature more than 25`() {
        val weather = createWeatherDegrees(temperature = 30.0)

        val result = hotWeatherState.condition(weather)

        assertTrue(result)
    }

    @Test
    fun `should condition return false when temperature less than 25`() {
        val weather = createWeatherDegrees(temperature = 20.0)

        val result = hotWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should getClothingList return hot clothes when weather is hot`() {
        val hotClothes = ClothingLists.clothingForHotWeather

        val result = hotWeatherState.getClothingLists()

        assertEquals(hotClothes, result)
    }
}