package pl.com.mmotak.simpleweather.data.repo

import pl.com.mmotak.simpleweather.data.model.Weather

interface WeatherRepository {
    suspend fun getWeather() : Weather?
}