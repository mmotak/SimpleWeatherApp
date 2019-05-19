package pl.com.mmotak.simpleweather.data.openweather.network

import android.util.Log
import pl.com.mmotak.simpleweather.data.model.Weather
import pl.com.mmotak.simpleweather.data.network.WeatherNetworkProvider
import pl.com.mmotak.simpleweather.data.openweather.network.model.OpenWeatherWeb
import pl.com.mmotak.simpleweather.data.openweather.toAppWeather

class OpenWeatherNetworkProvider : WeatherNetworkProvider {
    private val api = OpenWeatherApi()

    override suspend fun fetchWeather(cityName: String, languageCode: String): Weather {
        val w = api.getWeatherAsync(cityName, languageCode).await()
        return w.toWeather()
    }

    private fun OpenWeatherWeb.toWeather(): Weather {
        Log.d("Weather", this.toString())
        return toAppWeather()
    }
}