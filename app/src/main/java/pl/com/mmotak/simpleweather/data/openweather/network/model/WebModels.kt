package pl.com.mmotak.simpleweather.data.openweather.network.model

// This is the main data class
data class OpenWeatherWeb(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Long,
    val id: Long,
    val main: Main,
    val name: String,
    val sys: Sys,
    val visibility: Int,
    val weather: List<WeatherX>,
    val wind: Wind,
    val rain: Map<String, Int>,
    val snow: Map<String, Int>
)

data class Clouds(val all: Int)

data class Coord(val lat: Double, val lon: Double)

data class Main(
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Sys(
    val country: String,
    val id: Int,
    val message: Double,
    val sunrise: Long,
    val sunset: Long,
    val type: Int
)

data class WeatherX(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(val speed: Double, val degree: Int)