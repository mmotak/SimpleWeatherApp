package pl.com.mmotak.simpleweather.data.repo

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import pl.com.mmotak.simpleweather.data.db.CurrentWeatherDao
import pl.com.mmotak.simpleweather.data.db.converter.toAppWeather
import pl.com.mmotak.simpleweather.data.db.converter.toDbWeather
import pl.com.mmotak.simpleweather.data.model.Weather
import pl.com.mmotak.simpleweather.data.network.WeatherNetworkProvider
import java.lang.Exception

class WeatherRepositoryImpl(
    private val weatherNetworkProvider: WeatherNetworkProvider,
    private val weatherDao: CurrentWeatherDao
) : WeatherRepository {
    private val CITY = "Gliwice"
    private val duration = Duration.ofMinutes(30)

    override suspend fun getWeather(): Weather? {
        try {
            var weather = weatherDao.loadCurrentWeather(CITY)?.toAppWeather()
            if (canBeDownloaded(weather)) {
                progress()

                val weatherFromWeb = weatherNetworkProvider.fetchWeather(CITY, "pl")
                weatherDao.insert(weatherFromWeb.toDbWeather())
                weather = weatherDao.loadCurrentWeather(CITY)?.toAppWeather()
            }

            return weather
        } catch (e: Exception) {
            Log.e("Weather", e.message, e)
            return null
        }
    }

    private suspend fun progress() {
        for (i: Int in 0..100) {
            delay(100)
            Log.i("Weather", "[${Thread.currentThread().name}] Progress $i%")
        }
    }

    private fun canBeDownloaded(weather: Weather?) = weather?.lastUpdate?.minus(duration)?.isAfter(LocalDateTime.now()) ?: true
}