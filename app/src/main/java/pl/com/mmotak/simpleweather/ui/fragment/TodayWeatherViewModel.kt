package pl.com.mmotak.simpleweather.ui.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel;
import pl.com.mmotak.simpleweather.data.db.CurrentWeatherDao
import pl.com.mmotak.simpleweather.data.db.WeatherDatabase
import pl.com.mmotak.simpleweather.tools.coroutines.BackgroundScope
import pl.com.mmotak.simpleweather.data.model.Weather
import pl.com.mmotak.simpleweather.data.network.WeatherNetworkProvider
import pl.com.mmotak.simpleweather.data.openweather.network.OpenWeatherNetworkProvider
import pl.com.mmotak.simpleweather.data.repo.CoroutineWeatherRepository
import pl.com.mmotak.simpleweather.data.repo.WeatherRepository
import pl.com.mmotak.simpleweather.data.repo.WeatherRepositoryImpl

class TodayWeatherViewModel(private val weatherRepository : WeatherRepository) : ViewModel() {
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
