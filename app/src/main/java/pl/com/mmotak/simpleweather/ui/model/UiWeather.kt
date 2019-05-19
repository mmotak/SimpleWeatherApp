package pl.com.mmotak.simpleweather.ui.model

import arrow.core.None
import arrow.core.Option

data class UiWeather(
    val locationName: String,
    val dateTime: String,
    val description: Option<String> = None,
    val temperature: Option<String> = None,
    val wind: Option<String> = None,
    val visibility: Option<String> = None,
    val precipitation: Option<String> = None,
    val icon: Option<String> = None
)