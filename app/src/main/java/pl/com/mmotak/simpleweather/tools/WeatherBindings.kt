package pl.com.mmotak.simpleweather.tools

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import pl.com.mmotak.simpleweather.R
import pl.com.mmotak.simpleweather.model.Weather
import java.util.*

@BindingAdapter("weather_temperature")
fun TextView.setWeatherTemperature(weather: Weather?) {
    if(weather == null) {
        visibility = View.GONE
    }

    weather?.let {
        visibility = View.VISIBLE
        val temp = "%.1f".format(it.conditions.temperature - 273.15, Locale.getDefault())
        text = "${temp}°C"
    }
}

@BindingAdapter("weather_description")
fun TextView.setWeatherDescription(weather: Weather?) {
    if(weather == null) {
        visibility = View.GONE
    }

    weather?.let {
        visibility = View.VISIBLE
        text = it.descriptions.first().description
    }
}

@BindingAdapter("weather_wind")
fun TextView.setWeatherWind(weather: Weather?) {
    if(weather == null) {
        visibility = View.GONE
    }

    weather?.let {
        val wind = it.conditions.wind
        if (wind.isEmpty()) {
            visibility = View.GONE
        } else {
            visibility = View.VISIBLE
            text = "${context.getString(R.string.weather_wind)}: ${it.conditions.wind.createStringMessage()}"
        }
    }
}

@BindingAdapter("weather_precipitation")
fun TextView.setWeatherPrecipitation(weather: Weather?) {
    if(weather == null) {
        visibility = View.GONE
    }

    weather?.let {
        visibility = View.VISIBLE
        text = "${context.getString(R.string.weather_precipitation)}: ${it.conditions.rain}"
    }
}

@BindingAdapter("weather_visibility")
fun TextView.setWeatherVisibility(weather: Weather?) {
    if(weather == null) {
        visibility = View.GONE
    }

    weather?.let {
        visibility = View.VISIBLE
        text = "${context.getString(R.string.weather_visibility)}: ${it.conditions.visibility}m"
    }
}