package logic.state

import logic.createWeatherDegrees
import org.example.logic.utils.ClothingLists
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class UvIndexWeatherStateTest {

    private val uvIndexWeatherState = UvIndexWeatherState()

    @Test
    fun `should condition return true when uvIndex more than 7`() {
        val weather = createWeatherDegrees(uvIndex = 10.0)

        val result = uvIndexWeatherState.condition(weather)

        assertTrue(result)
    }

    @Test
    fun `should condition return false when uvIndex less than 7`() {
        val weather = createWeatherDegrees(uvIndex = 5.0)

        val result = uvIndexWeatherState.condition(weather)

        assertFalse(result)
    }

    @Test
    fun `should getClothingLists return uvIndex Clothes when weather is have uvIndex`() {
        val uvIndexClothes = ClothingLists.clothingForUvIndexWeather

        val result = uvIndexWeatherState.getClothingLists()

        assertEquals(uvIndexClothes, result)
    }
}