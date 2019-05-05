package pl.com.mmotak.simpleweather.repo

import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import pl.com.mmotak.simpleweather.model.Weather
import pl.com.mmotak.simpleweather.network.WeatherNetworkProvider

class WeatherRepositoryImpl(
    private val weatherNetworkProvider: WeatherNetworkProvider
) : WeatherRepository {
    private val duration = Duration.ofMinutes(30)
    private var _weather: Weather? = null

    init {
        weatherNetworkProvider.weather.observeForever {
            _weather = it
        }
    }

    override fun getWeather(): LiveData<Weather> {
        updateData()
        return weatherNetworkProvider.weather
    }

    private fun updateData() {
        if (canBeDownloaded()) {
            GlobalScope.launch {
                weatherNetworkProvider.fetchWeather(
                    "Gliwice",
                    "pl"
                )
            }
        }
    }

    private fun canBeDownloaded()
            = _weather?.lastUpdate?.minus(duration)?.isAfter(LocalDateTime.now()) ?: true
}