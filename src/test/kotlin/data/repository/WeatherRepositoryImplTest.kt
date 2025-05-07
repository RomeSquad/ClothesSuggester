package data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import logic.entity.Weather
import logic.exceptions.DataError
import logic.exceptions.NetworkError
import org.example.data.api.GeocodingApi
import org.example.data.api.WeatherApi
import org.example.data.api.models.GeocodingDto
import org.example.data.api.models.WeatherDto
import org.example.data.repository.WeatherRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class WeatherRepositoryImplTest {

    private lateinit var weatherRepository: WeatherRepositoryImpl
    private val weatherApi: WeatherApi = mockk()
    private val geocodingApi: GeocodingApi = mockk()

    @BeforeEach
    fun setUp() {
        weatherRepository = WeatherRepositoryImpl(weatherApi, geocodingApi)
    }

    @Test
    fun `getWeather should return Weather when city is valid and APIs succeed`() = runTest {
        val city = "London"
        val coordinates = GeocodingDto(latitude = 51.5074, longitude = -0.1278)
        val weatherDto = WeatherDto(
            temperature = 15.0,
            precipitation = 0.2,
            windSpeed = 10.0,
            humidity = 80.0,
            uvIndex = 3.0
        )
        val expectedWeather = Weather(
            temperature = 15.0,
            precipitation = 0.2,
            windSpeed = 10.0,
            humidity = 80.0,
            uvIndex = 3.0
        )
        coEvery { geocodingApi.fetchCoordinates(city) } returns coordinates
        coEvery { weatherApi.fetchWeather(coordinates.latitude, coordinates.longitude) } returns weatherDto

        val result = weatherRepository.getWeather(city)

        assertEquals(expectedWeather, result)
    }

    @Test
    fun `getWeather should return Weather with default uvIndex when uvIndex is null`() = runTest {
        val city = "London"
        val coordinates = GeocodingDto(latitude = 51.5074, longitude = -0.1278)
        val weatherDto = WeatherDto(
            temperature = 15.0,
            precipitation = 0.2,
            windSpeed = 10.0,
            humidity = 80.0,
            uvIndex = null
        )
        val expectedWeather = Weather(
            temperature = 15.0,
            precipitation = 0.2,
            windSpeed = 10.0,
            humidity = 80.0,
            uvIndex = 0.0
        )
        coEvery { geocodingApi.fetchCoordinates(city) } returns coordinates
        coEvery { weatherApi.fetchWeather(coordinates.latitude, coordinates.longitude) } returns weatherDto

        val result = weatherRepository.getWeather(city)

        assertEquals(expectedWeather, result)
    }

    @Test
    fun `getWeather should throw DataError when city is blank`() = runTest {
        val city = ""

        val exception = assertThrows<DataError> {
            weatherRepository.getWeather(city)
        }
        assertEquals("City name cannot be empty", exception.message)
    }

    @Test
    fun `getWeather should throw DataError when city is whitespace`() = runTest {
        val city = "   "

        val exception = assertThrows<DataError> {
            weatherRepository.getWeather(city)
        }
        assertEquals("City name cannot be empty", exception.message)
    }

    @Test
    fun `getWeather should throw NetworkError when geocodingApi throws NetworkError`() = runTest {
        val city = "London"
        coEvery { geocodingApi.fetchCoordinates(city) } throws NetworkError("Connection failed")

        val exception = assertThrows<NetworkError> {
            weatherRepository.getWeather(city)
        }
        assertEquals("Failed to fetch weather for city 'London': Connection failed", exception.message)
    }

    @Test
    fun `getWeather should throw NetworkError when weatherApi throws NetworkError`() = runTest {
        val city = "London"
        val coordinates = GeocodingDto(latitude = 51.5074, longitude = -0.1278)
        coEvery { geocodingApi.fetchCoordinates(city) } returns coordinates
        coEvery {
            weatherApi.fetchWeather(
                coordinates.latitude,
                coordinates.longitude
            )
        } throws NetworkError("API timeout")

        val exception = assertThrows<NetworkError> {
            weatherRepository.getWeather(city)
        }
        assertEquals("Failed to fetch weather for city 'London': API timeout", exception.message)
    }

    @Test
    fun `getWeather should throw DataError when geocodingApi throws DataError`() = runTest {
        val city = "London"
        coEvery { geocodingApi.fetchCoordinates(city) } throws DataError("Invalid city name")

        val exception = assertThrows<DataError> {
            weatherRepository.getWeather(city)
        }
        assertEquals("Invalid weather data for city 'London': Invalid city name", exception.message)
    }

    @Test
    fun `getWeather should throw DataError when weatherApi throws DataError`() = runTest {
        val city = "London"
        val coordinates = GeocodingDto(latitude = 51.5074, longitude = -0.1278)
        coEvery { geocodingApi.fetchCoordinates(city) } returns coordinates
        coEvery {
            weatherApi.fetchWeather(
                coordinates.latitude,
                coordinates.longitude
            )
        } throws DataError("Invalid weather data")

        val exception = assertThrows<DataError> {
            weatherRepository.getWeather(city)
        }
        assertEquals("Invalid weather data for city 'London': Invalid weather data", exception.message)
    }

    @Test
    fun `getWeather should throw DataError for unexpected exceptions`() = runTest {
        val city = "London"
        coEvery { geocodingApi.fetchCoordinates(city) } throws RuntimeException("Unexpected error")

        val exception = assertThrows<DataError> {
            weatherRepository.getWeather(city)
        }
        assertEquals("An unexpected error occurred: Unexpected error", exception.message)
    }
}