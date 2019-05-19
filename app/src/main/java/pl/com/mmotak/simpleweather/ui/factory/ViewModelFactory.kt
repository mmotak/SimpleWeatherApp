package pl.com.mmotak.simpleweather.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.com.mmotak.simpleweather.data.repo.WeatherRepository
import pl.com.mmotak.simpleweather.ui.fragment.TodayWeatherViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val weatherRepository: WeatherRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodayWeatherViewModel::class.java)) {
            return TodayWeatherViewModel(weatherRepository) as T
        }
        return super.create(modelClass)
    }
}