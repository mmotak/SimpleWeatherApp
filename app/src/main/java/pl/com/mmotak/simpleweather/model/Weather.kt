package pl.com.mmotak.simpleweather.model

import org.threeten.bp.LocalDateTime

data class Weather(
    val city : String,
    val temp: Double,
    val lastUpdate: LocalDateTime = LocalDateTime.now()
)