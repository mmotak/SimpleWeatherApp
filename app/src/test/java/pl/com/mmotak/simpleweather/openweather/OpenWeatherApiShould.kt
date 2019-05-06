package pl.com.mmotak.simpleweather.openweather

import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import pl.com.mmotak.simpleweather.openweather.network.OpenWeatherApi

class OpenWeatherApiShould {
    lateinit var api: OpenWeatherApi

    @Before
    fun before() {
        api = OpenWeatherApi.invoke()
    }

    @Test
    fun downloadTheWeather() {
        runBlocking {
            var output = api.getWeather("London").await()
            println(output)
            output.name `should be equal to` "London"
        }
    }
}