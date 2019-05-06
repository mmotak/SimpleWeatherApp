package pl.com.mmotak.simpleweather.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import pl.com.mmotak.simpleweather.coroutines.BackgroundScope
import pl.com.mmotak.simpleweather.model.Weather
import pl.com.mmotak.simpleweather.network.WeatherNetworkProvider
import pl.com.mmotak.simpleweather.openweather.network.OpenWeatherNetworkProvider
import pl.com.mmotak.simpleweather.repo.CoroutineWeatherRepository
import pl.com.mmotak.simpleweather.repo.WeatherRepository
import pl.com.mmotak.simpleweather.repo.WeatherRepositoryImpl

class TodayWeatherViewModel : ViewModel() {
    private val weatherNetworkProvider: WeatherNetworkProvider = OpenWeatherNetworkProvider()
    private val weatherRepository : WeatherRepository = WeatherRepositoryImpl(weatherNetworkProvider)
    private val scopeDelegate = lazy { BackgroundScope() }
    private val scope by scopeDelegate

    init {
        if (weatherRepository is CoroutineWeatherRepository) {
            scope.onStart()
            weatherRepository.setScope(scope)
        }
    }

    val weather: LiveData<Weather>
        get() = weatherRepository.getWeather()

    override fun onCleared() {
        Log.d("Weather", "onCleared")
        if (scopeDelegate.isInitialized()) {
            scope.onStop()
        }
        super.onCleared()
    }
}
