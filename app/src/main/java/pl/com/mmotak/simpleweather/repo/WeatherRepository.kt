package pl.com.mmotak.simpleweather.repo

import androidx.lifecycle.LiveData
import pl.com.mmotak.simpleweather.model.Weather

interface WeatherRepository {
    fun getWeather() : LiveData<Weather>
}