package logic.state

import logic.createWeatherDegrees
import org.example.logic.utils.ClothingLists
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class FreezingWeatherStateTest {

    private val freezingWeatherState = FreezingWeatherState()

    @Test
    fun `should condition return true when temperature less than 0`() {
        val weather = createWeatherDegrees(temperature = -1.0)

        val result = freezingWeatherState.condition(weather)

        assertTrue(result)
    }

    @Test
    fun `should condition return false when temperature more than 0`() {
        val weather = createWeatherDegrees(temperature = 5.0)

        val result = freezingWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should getClothingLists returns freeze clothes when weather is freeze`() {
        val freezeClothes = ClothingLists.clothingForFreezeWeather

        val result = freezingWeatherState.getClothingLists()

        assertEquals(freezeClothes, result)
    }
}