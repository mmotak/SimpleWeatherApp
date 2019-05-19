package pl.com.mmotak.simpleweather.data.repo

import androidx.lifecycle.LiveData
import pl.com.mmotak.simpleweather.data.model.Weather

class MemoryWeatherRepository : WeatherRepository {
    override fun getWeather(): LiveData<Weather> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}