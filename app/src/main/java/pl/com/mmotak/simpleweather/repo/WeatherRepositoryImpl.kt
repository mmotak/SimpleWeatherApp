package pl.com.mmotak.simpleweather.repo

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import pl.com.mmotak.simpleweather.model.Weather
import pl.com.mmotak.simpleweather.network.WeatherNetworkProvider

class WeatherRepositoryImpl(
    private val weatherNetworkProvider: WeatherNetworkProvider
) : CoroutineWeatherRepository {

    private val duration = Duration.ofMinutes(30)
    private var weather: Weather? = null
    private var scope: CoroutineScope? = null
    private var currentJob : Job? = null

    init {
        weatherNetworkProvider.weather.observeForever {
            weather = it
        }
    }

    override fun setScope(scope: CoroutineScope) {
        Log.d("Weather", "setScope $scope")
        this.scope = scope
    }

    override fun getWeather(): LiveData<Weather> {
        updateData()
        return weatherNetworkProvider.weather
    }

    private fun updateData() {
        if (canBeDownloaded()) {
            Log.w("Weather", "start updateData")
            currentJob = scope?.launch {
                progress()

                weatherNetworkProvider.fetchWeather(
                    "Gliwice",
                    "pl"
                )
            }
        }
    }

    private suspend fun progress() {
        for (i: Int in 0..100) {
            delay(10)
            Log.i("Weather","[${Thread.currentThread().name}] Progress $i%")
        }
    }

    private fun canBeDownloaded()
            = currentJob?.isCompleted ?: true && weather?.lastUpdate?.minus(duration)?.isAfter(LocalDateTime.now()) ?: true
}