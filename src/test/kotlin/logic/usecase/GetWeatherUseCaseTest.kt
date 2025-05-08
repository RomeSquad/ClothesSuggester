package logic.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import logic.entity.Weather
import org.example.logic.repository.WeatherRepository
import org.example.logic.usecase.GetWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class GetWeatherUseCaseTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getWeatherUseCase: GetWeatherUseCase

    @BeforeEach
    fun setup() {
        weatherRepository = mockk()
        getWeatherUseCase = GetWeatherUseCase(weatherRepository)
    }

    @Test
    fun `should return weather when city is valid`() = runTest {
        val weather = Weather(10.0,10.0,10.0,10.0,10.0)
        coEvery { weatherRepository.getWeather("Cairo") } returns weather

        val result = getWeatherUseCase.getWeather("Cairo")

        assertEquals(weather, result)
    }
}