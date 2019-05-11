package pl.com.mmotak.simpleweather.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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

    val weather: LiveData<Weather>
        get() = weatherRepository.getWeather()

    val isLoading = MediatorLiveData<Boolean>()

    init {
        if (weatherRepository is CoroutineWeatherRepository) {
            scope.onStart()
            weatherRepository.setScope(scope)
        }
        isLoading.value = true
        isLoading.addSource(weather) { w : Weather? ->
            isLoading.value = w == null
        }
    }

    override fun onCleared() {
        Log.d("Weather", "onCleared")
        if (scopeDelegate.isInitialized()) {
            scope.onStop()
        }
        super.onCleared()
    }
}
