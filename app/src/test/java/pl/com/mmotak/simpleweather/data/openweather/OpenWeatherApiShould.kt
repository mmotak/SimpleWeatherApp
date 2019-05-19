package pl.com.mmotak.simpleweather.data.openweather

import kotlinx.coroutines.runBlocking
import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test
import pl.com.mmotak.simpleweather.data.openweather.network.OpenWeatherApi

class OpenWeatherApiShould {
    lateinit var api: OpenWeatherApi

    @Before
    fun before() {
        api = OpenWeatherApi.invoke()
    }

    @Test
    fun downloadTheWeather() {
        runBlocking {
            var output = api.getWeatherAsync("London").await()
            println(output)
            output.name `should be equal to` "London"
        }
    }
}