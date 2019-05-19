package pl.com.mmotak.simpleweather.data.repo

import kotlinx.coroutines.CoroutineScope

interface CoroutineWeatherRepository : WeatherRepository {
    fun setScope(scope: CoroutineScope)
}