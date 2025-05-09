package logic.state

import logic.createWeatherDegrees
import org.example.logic.utils.ClothingLists
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RainyWeatherStateTest {

    private val rainyWeatherState = RainyWeatherState()

    @Test
    fun `should condition return true when precipitation more than value`() {
        val weather = createWeatherDegrees(precipitation = 1.0)

        val result = rainyWeatherState.condition(weather)

        assertTrue(result)
    }

    @Test
    fun `should condition return false when precipitation less than value`() {
        val weather = createWeatherDegrees(precipitation = 0.3)

        val result = rainyWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should getClothingLists return rainy clothes when weather is rainy`() {
        val rainyClothes = ClothingLists.clothingForRainyWeather

        val result = rainyWeatherState.getClothingLists()

        assertEquals(rainyClothes, result)
    }
}