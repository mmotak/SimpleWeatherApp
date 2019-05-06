package pl.com.mmotak.simpleweather.repo

import kotlinx.coroutines.CoroutineScope

interface CoroutineWeatherRepository : WeatherRepository {
    fun setScope(scope: CoroutineScope)
}