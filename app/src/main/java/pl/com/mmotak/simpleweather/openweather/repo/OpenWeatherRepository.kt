package pl.com.mmotak.simpleweather.openweather.repo

import androidx.lifecycle.LiveData
import pl.com.mmotak.simpleweather.model.Weather
import pl.com.mmotak.simpleweather.repo.WeatherRepository

class OpenWeatherRepository : WeatherRepository {
    override fun getWeather(): LiveData<Weather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}