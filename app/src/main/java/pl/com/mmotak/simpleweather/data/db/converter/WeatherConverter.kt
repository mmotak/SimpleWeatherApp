package pl.com.mmotak.simpleweather.data.db.converter

import pl.com.mmotak.simpleweather.data.db.model.*
import pl.com.mmotak.simpleweather.data.model.*

fun Weather.toDbWeather() : WeatherDb {
    return WeatherDb(
        location = LocationDb(
            name = location.name,
            latitude = location.latitude,
            longitude = location.longitude,
            country = location.country
        ),
        conditions = ConditionsDb(
            temperature = conditions.temperature,
            pressure = conditions.pressure,
            humidity = conditions.humidity,
            clouds = conditions.clouds,
            visibility = conditions.visibility,
            wind = WindDb(
                speed = conditions.wind.speed,
                degrees = conditions.wind.degrees
            ),
            rain = conditions.rain.toVolumeDb(),
            snow = conditions.snow.toVolumeDb(),
            sunrise = conditions.sunrise,
            sunset = conditions.sunset
        ),
        descriptions = descriptions.map { DescriptionDb(it.short, it.description, it.icon) },
        systemDateTime = systemDateTime,
        lastUpdate = lastUpdate
    )
}

private fun Volume.toVolumeDb() = VolumeDb(mm,h)

fun WeatherDb.toAppWeather(): Weather {
    return Weather(
        location = Location(
            name = location.name,
            latitude = location.latitude,
            longitude = location.longitude,
            country = location.country
        ),
        conditions = Conditions(
            temperature = conditions.temperature,
            pressure = conditions.pressure,
            humidity = conditions.humidity,
            clouds = conditions.clouds,
            visibility = conditions.visibility,
            wind = Wind(
                speed = conditions.wind.speed,
                degrees = conditions.wind.degrees
            ),
            rain = conditions.rain.toVolume(),
            snow = conditions.snow.toVolume(),
            sunrise = conditions.sunrise,
            sunset = conditions.sunset
        ),
        descriptions = descriptions.map { Description(it.short, it.description, it.icon) },
        systemDateTime = systemDateTime,
        lastUpdate = lastUpdate
    )
}

private fun VolumeDb.toVolume() = Volume(mm, h)
