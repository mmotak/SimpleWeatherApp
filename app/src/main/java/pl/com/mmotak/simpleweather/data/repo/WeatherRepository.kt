package pl.com.mmotak.simpleweather.data.repo

import androidx.lifecycle.LiveData
import pl.com.mmotak.simpleweather.data.model.Weather

interface WeatherRepository {
    fun getWeather() : LiveData<Weather>
}