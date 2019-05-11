package pl.com.mmotak.simpleweather.openweather

import org.threeten.bp.*
import pl.com.mmotak.simpleweather.model.*
import pl.com.mmotak.simpleweather.openweather.network.model.OpenWeatherWeb

private fun Long.toZonedDateTime(zoneId: ZoneId = ZoneOffset.UTC) : ZonedDateTime {
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(this), zoneId)
}

private fun Long.toLocalDateTime(zoneId: ZoneId = ZoneOffset.UTC): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(this), zoneId)
}

private fun Map<String, Int>?.toVolume(): Volume {
    if (this == null || this.isEmpty()) {
        return Volume(0,0)
    }

    return Volume(values.first(), keys.first().take(1).toInt())
}

fun OpenWeatherWeb.toAppWeather(): Weather {
    return Weather(
        location = Location(
            name = this.name,
            coordinate = Coordinate(this.coord.lat, this.coord.lon),
            country = this.sys.country
        ),
        conditions = Conditions(
            temperature = this.main.temp,
            pressure = this.main.pressure,
            humidity = this.main.humidity,
            clouds = this.clouds.all,
            visibility = this.visibility,
            wind = Wind(
                speed = this.wind.speed,
                degrees = this.wind.degree.toDouble()
            ),
            rain = this.rain.toVolume(),
            snow = this.snow.toVolume(),
            sunrise = this.sys.sunrise.toZonedDateTime(),
            sunset = this.sys.sunset.toZonedDateTime()
        ),
        descriptions = this.weather.map { Description(it.main, it.description, it.icon) },
        systemDateTime = this.dt.toZonedDateTime()
    )
}
