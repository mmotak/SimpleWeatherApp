package pl.com.mmotak.simpleweather.data.network

import pl.com.mmotak.simpleweather.data.model.Weather

interface WeatherNetworkProvider {
    suspend fun fetchWeather(cityName: String, languageCode: String) : Weather
}