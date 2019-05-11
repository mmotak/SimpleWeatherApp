package pl.com.mmotak.simpleweather.openweather

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import org.threeten.bp.*
import pl.com.mmotak.simpleweather.model.*
import pl.com.mmotak.simpleweather.openweather.network.model.OpenWeatherWeb

class Converters {
    companion object {
        const val inputJson=
"""{
  "coord": {
    "lon": -0.13,
    "lat": 51.51
  },
  "weather": [
    {
      "id": 521,
      "main": "Rain",
      "description": "deszcz",
      "icon": "09d"
    }
  ],
  "base": "stations",
  "main": {
    "temp": 285.4,
    "pressure": 1019,
    "humidity": 81,
    "temp_min": 283.15,
    "temp_max": 287.59
  },
  "visibility": 10000,
  "wind": {
    "speed": 2.6,
    "deg": 10
  },
  "clouds": {
    "all": 10
  },
  "dt": 1557570604,
  "sys": {
    "type": 1,
    "id": 1414,
    "message": 0.3053,
    "country": "GB",
    "sunrise": 1557548128,
    "sunset": 1557603505
  },
  "id": 2643743,
  "name": "London",
  "cod": 200
}"""
    }

    @Test
    fun `Open Weather Web Model should convert to App Weather Model`() {
        val web: OpenWeatherWeb = Gson().fromJson(inputJson, OpenWeatherWeb::class.java)
        Assert.assertNotNull(web)

        val appModel: Weather = web.toAppWeather()
        Assert.assertNotNull(appModel)
    }
}

