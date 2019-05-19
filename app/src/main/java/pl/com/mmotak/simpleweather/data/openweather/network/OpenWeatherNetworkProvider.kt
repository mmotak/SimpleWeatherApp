package pl.com.mmotak.simpleweather.data.openweather.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.com.mmotak.simpleweather.data.model.Weather
import pl.com.mmotak.simpleweather.data.network.WeatherNetworkProvider
import pl.com.mmotak.simpleweather.data.openweather.network.model.OpenWeatherWeb
import pl.com.mmotak.simpleweather.data.openweather.toAppWeather
import java.lang.Exception

class OpenWeatherNetworkProvider : WeatherNetworkProvider {
    override val weather: LiveData<Weather>
        get() = _weather

    private val _weather = MutableLiveData<Weather>()
    private val api = OpenWeatherApi()

    override suspend fun fetchWeather(cityName: String, languageCode: String) {
        // TODO check languange code
        try {
            val w = api.getWeatherAsync(cityName, languageCode).await()
            _weather.postValue(w.toWeather())
        } catch (e : Exception) {
            Log.e("Weather", e.message, e)
        }
    }

    private fun OpenWeatherWeb.toWeather() : Weather {
        Log.d("Weather", this.toString() )
        return toAppWeather()
    }
}