package pl.com.mmotak.simpleweather.data.model

import androidx.room.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import pl.com.mmotak.simpleweather.data.db.*

@Entity(tableName = "weather_table")
data class Weather (
    @Embedded(prefix = "location_")
    val location: Location,
    @Embedded(prefix = "conditions_")
    val conditions: Conditions,
    @TypeConverters(DescriptionsDbConverter::class)
    val descriptions: List<Description>,
    @TypeConverters(ZonedDateTimeDbConverter::class)
    val systemDateTime: ZonedDateTime,
    @TypeConverters(LocalDateTimeDbConverter::class)
    val lastUpdate: LocalDateTime = LocalDateTime.now()
) {
    @PrimaryKey(autoGenerate = false)
    var id : String = location.name
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
    @TypeConverters(WindDbConverter::class)
    val wind: Wind,
    @TypeConverters(VolumeDbConverter::class)
    val rain: Volume,
    @TypeConverters(VolumeDbConverter::class)
    val snow: Volume,
    @TypeConverters(ZonedDateTimeDbConverter::class)
    val sunrise: ZonedDateTime,
    @TypeConverters(ZonedDateTimeDbConverter::class)
    val sunset: ZonedDateTime
)

data class Wind(
    val speed: Double?, // m/s
    val degrees: Double?,
    val direction: CardinalDirection?
) {
    @Ignore
    fun isEmpty(): Boolean = speed == null

    @Ignore
    fun createStringMessage(): String {
        return when {
            speed == null -> "No wind"
            degrees == null -> "$speed m/s"
            direction == null -> "$speed m/s"
            else -> "$direction (${degrees.toInt()}) $speed m/s"
        }
    }

    @Ignore
    constructor(speed: Double?, degrees: Double?) : this(speed, degrees, CardinalDirection.fromDegrees(degrees))
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