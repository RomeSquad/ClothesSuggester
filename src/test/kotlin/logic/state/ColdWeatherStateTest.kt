package logic.state

import logic.createWeatherDegrees
import org.example.logic.utils.ClothingLists
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class ColdWeatherStateTest {

    private val coldWeatherState = ColdWeatherState()

    @Test
    fun `should condition return true when temperature within cold range`() {
        val weather = createWeatherDegrees(temperature = 5.0)

        val result = coldWeatherState.condition(weather)

        assertTrue(result)
    }

    @Test
    fun `should condition return false when temperature below cold range`() {
        val weather = createWeatherDegrees(temperature = -5.0)

        val result = coldWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should condition return false when temperature outside cold range`() {
        val weather = createWeatherDegrees(temperature = 15.0)

        val result = coldWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should getClothingLists returns cold clothes when weather is cold`() {
        val coldClothingList = ClothingLists.clothingForColdWeather

        val result = coldWeatherState.getClothingLists()

        assertEquals(coldClothingList, result)
    }
}