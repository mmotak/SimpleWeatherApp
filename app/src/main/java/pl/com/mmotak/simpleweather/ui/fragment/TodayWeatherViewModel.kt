package pl.com.mmotak.simpleweather.ui.fragment

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import arrow.core.toOption
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pl.com.mmotak.simpleweather.R
import pl.com.mmotak.simpleweather.SimpleWeatherApplication
import pl.com.mmotak.simpleweather.tools.coroutines.BackgroundScope
import pl.com.mmotak.simpleweather.data.model.Weather
import pl.com.mmotak.simpleweather.data.repo.WeatherRepository
import pl.com.mmotak.simpleweather.ui.model.UiWeather
import java.util.*

class TodayWeatherViewModel(
    private val weatherRepository : WeatherRepository,
    application : Application)
    : AndroidViewModel(application) {
    private val scope by lazy { BackgroundScope() }
    private var _weather = MutableLiveData<UiWeather>()
    private var job: Job? = null

    val weather: LiveData<UiWeather>
        get() = getWeatherFromRepo()

    val isLoading = MediatorLiveData<Boolean>()

    private val app = getApplication<SimpleWeatherApplication>()

    init {
        scope.onStart()
        isLoading.value = true
        isLoading.addSource(_weather) { w : UiWeather? ->
            isLoading.value = w == null
        }
    }

    private fun getWeatherFromRepo(): LiveData<UiWeather> {
        fetchWeather()
        return _weather
    }

    private fun fetchWeather() {
        if (job == null || job?.isCompleted == true) {
            job = scope.launch {
                weatherRepository.getWeather()?.let {
                    val uiWeather = it.toUiWeather(app)
                    if (uiWeather != _weather.value) {
                        _weather.postValue(uiWeather)
                    }
                }
            }
        }
    }


    override fun onCleared() {
        Log.d("Weather", "onCleared")
        scope.onStop()
        super.onCleared()
    }
}

private fun Weather.toUiWeather(context: Context): UiWeather {
    return UiWeather(
        locationName = location.name,
        dateTime = lastUpdate.toString(), // TODO datetime formatter
        description = descriptions.firstOrNull()?.description.toOption(),
        temperature = conditions.temperature.toC().toOption(),
        wind = conditions.wind.createStringMessage().toOption(),
        visibility = "${conditions.visibility} m".toOption(),
        precipitation = "${context.getString(R.string.weather_precipitation)}: ${conditions.rain}".toOption(),
        icon = descriptions.firstOrNull()?.icon.toOption()
    )
}

private fun Double.toC() : String {
    val temp = "%.1f".format(this - 273.15, Locale.getDefault())
    return "${temp}°C"
}
