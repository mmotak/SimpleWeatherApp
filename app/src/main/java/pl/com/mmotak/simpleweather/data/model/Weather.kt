package pl.com.mmotak.simpleweather.data.model

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime

data class Weather (
    val location: Location,
    val conditions: Conditions,
    val descriptions: List<Description>,
    val systemDateTime: ZonedDateTime,
    val lastUpdate: LocalDateTime
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Weather

        return (lastUpdate == other.lastUpdate
                && location.name.toLowerCase() == other.location.name.toLowerCase())
    }

    override fun hashCode(): Int {
        var result = location.hashCode()
        result = 31 * result + conditions.hashCode()
        result = 31 * result + descriptions.hashCode()
        result = 31 * result + systemDateTime.hashCode()
        result = 31 * result + lastUpdate.hashCode()
        return result
    }
}

data class Description(
    val short: String,
    val description: String,
    val icon: String
)

data class Conditions(
    val temperature: Double, // K
    val pressure: Int, // hPa
    val humidity: Int, // %
    val clouds: Int, // %
    val visibility: Int, // M ?
    val wind: Wind,
    val rain: Volume,
    val snow: Volume,
    val sunrise: ZonedDateTime,
    val sunset: ZonedDateTime
)

data class Wind(
    val speed: Double?, // m/s
    val degrees: Double?,
    val direction: CardinalDirection? = CardinalDirection.fromDegrees(degrees)
) {
    fun isEmpty(): Boolean = speed == null

    fun createStringMessage(): String {
        return when {
            speed == null -> "No wind"
            degrees == null -> "$speed m/s"
            direction == null -> "$speed m/s"
            else -> "$direction (${degrees.toInt()}) $speed m/s"
        }
    }
}

data class Volume(val mm: Int, val h: Int) {
    override fun toString(): String {
        return when {
            mm == 0 -> "0 mm"
            h == 0 -> "0 mm"
            else -> "${mm/h} mm"
        }
    }
}

data class Location(
    val name: String, // city name or similar
    val country: String,
    val latitude: Double,
    val longitude: Double
)

enum class CardinalDirection {
    N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW;

    companion object {
        fun fromDegrees(degrees: Double?) : CardinalDirection? {
            if (degrees == null) {
                return null
            }

            return when(degrees) {
                in 0.0..11.25 -> N
                in 348.75..360.0 -> N
                //in 348.75..11.25 -> N
                in 11.25..33.75 -> NNE
                in 33.75..56.25 -> NE
                in 56.25..78.75 -> ENE
                in 78.75..101.25 -> E
                in 101.25..123.75 -> ESE
                in 123.75..146.25 -> SE
                in 146.25..168.75 -> SSE
                in 168.75..191.25 -> S
                in 191.25..213.75 -> SSW
                in 213.75..236.25 -> SW
                in 236.25..258.75 -> WSW
                in 258.75..281.25 -> W
                in 281.25..303.75 -> WNW
                in 303.75..326.25 -> NW
                in 326.25..348.75 -> NNW
                else -> null
            }
        }
    }
}