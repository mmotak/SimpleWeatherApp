package pl.com.mmotak.simpleweather.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import pl.com.mmotak.simpleweather.model.Weather
import pl.com.mmotak.simpleweather.network.WeatherNetworkProvider
import pl.com.mmotak.simpleweather.openweather.network.OpenWeatherNetworkProvider
import pl.com.mmotak.simpleweather.repo.WeatherRepository
import pl.com.mmotak.simpleweather.repo.WeatherRepositoryImpl

class TodayWeatherViewModel : ViewModel() {
    private val weatherNetworkProvider: WeatherNetworkProvider = OpenWeatherNetworkProvider()
    private val weatherRepository : WeatherRepository = WeatherRepositoryImpl(weatherNetworkProvider)

    val weather: LiveData<Weather>
        get() = weatherRepository.getWeather()
}
