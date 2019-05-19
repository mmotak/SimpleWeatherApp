package pl.com.mmotak.simpleweather.ui.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import kotlinx.coroutines.launch
import pl.com.mmotak.simpleweather.tools.coroutines.BackgroundScope
import pl.com.mmotak.simpleweather.data.model.Weather
import pl.com.mmotak.simpleweather.data.repo.WeatherRepository

class TodayWeatherViewModel(private val weatherRepository : WeatherRepository) : ViewModel() {
    private val scope by lazy { BackgroundScope() }
    private var _weather = MutableLiveData<Weather>()

    val weather: LiveData<Weather>
        get() = getWeatherFromRepo()

    val isLoading = MediatorLiveData<Boolean>()

    init {
        scope.onStart()
        isLoading.value = true
        isLoading.addSource(_weather) { w : Weather? ->
            isLoading.value = w == null
        }
    }

    private fun getWeatherFromRepo(): LiveData<Weather> {
        fetchWeather()
        return _weather
    }

    private fun fetchWeather() {
        scope.launch {
            val weather = weatherRepository.getWeather()
            if (weather != _weather.value) {
                _weather.postValue(weather)
            }
        }
    }


    override fun onCleared() {
        Log.d("Weather", "onCleared")
        scope.onStop()
        super.onCleared()
    }
}
