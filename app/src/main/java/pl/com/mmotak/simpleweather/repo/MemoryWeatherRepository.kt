package pl.com.mmotak.simpleweather.repo

import androidx.lifecycle.LiveData
import pl.com.mmotak.simpleweather.model.Weather

class MemoryWeatherRepository : WeatherRepository {
    override fun getWeather(): LiveData<Weather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}