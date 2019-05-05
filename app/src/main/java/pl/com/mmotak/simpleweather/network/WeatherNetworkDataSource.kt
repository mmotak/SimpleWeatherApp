package pl.com.mmotak.simpleweather.network

import androidx.lifecycle.LiveData
import pl.com.mmotak.simpleweather.model.Weather

interface WeatherNetworkProvider {
    val weather: LiveData<Weather>

    suspend fun fetchWeather(cityName: String, languageCode: String)
}