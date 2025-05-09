package logic.state

import logic.createWeatherDegrees
import org.example.logic.utils.ClothingLists
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class WarmWeatherStateTest {

    private val warmWeatherState = WarmWeatherState()

    @Test
    fun `should condition return true when temperature within warm range`() {
        val weather = createWeatherDegrees(temperature = 15.0)

        val result = warmWeatherState.condition(weather)

        assertTrue(result)
    }

    @Test
    fun `should condition return false when temperature below warm range`() {
        val weather = createWeatherDegrees(temperature = 5.0)

        val result = warmWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should condition return false when temperature outside warm range`() {
        val weather = createWeatherDegrees(temperature = 30.0)

        val result = warmWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should getClothingLists returns warm clothes when weather is warm`() {
        val warmClothingList = ClothingLists.clothingForWarmWeather

        val result = warmWeatherState.getClothingLists()

        assertEquals(warmClothingList, result)
    }
}