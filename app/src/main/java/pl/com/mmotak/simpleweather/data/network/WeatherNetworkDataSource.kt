package pl.com.mmotak.simpleweather.data.network

import androidx.lifecycle.LiveData
import pl.com.mmotak.simpleweather.data.model.Weather

interface WeatherNetworkProvider {
    val weather: LiveData<Weather>

    suspend fun fetchWeather(cityName: String, languageCode: String)
}