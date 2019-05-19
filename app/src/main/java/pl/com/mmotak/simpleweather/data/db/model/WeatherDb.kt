package pl.com.mmotak.simpleweather.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime


@Entity(tableName = "weather_db_table")
data class WeatherDb(
    @Embedded(prefix = "location_")
    val location: LocationDb,
    @Embedded(prefix = "conditions_")
    val conditions: ConditionsDb,
    //@TypeConverters(DescriptionDbConverter::class)
    val descriptions: List<DescriptionDb>,
    //@TypeConverters(ZonedDateTimeDbConverter::class)
    val systemDateTime: ZonedDateTime,
    //@TypeConverters(LocalDateTimeDbConverter::class)
    val lastUpdate: LocalDateTime = LocalDateTime.now(),
    @PrimaryKey(autoGenerate = false)
    val id : String = location.name
)

data class LocationDb (
    val name: String, // city name or similar
    val country: String,
    val latitude: Double,
    val longitude: Double
)

data class ConditionsDb(
    val temperature: Double, // K
    val pressure: Int, // hPa
    val humidity: Int, // %
    val clouds: Int, // %
    val visibility: Int, // M ?
    //@TypeConverters(WindDbConverter::class)
    val wind: WindDb,
    //@TypeConverters(VolumeDbConverter::class)
    val rain: VolumeDb,
    //@TypeConverters(VolumeDbConverter::class)
    val snow: VolumeDb,
    //@TypeConverters(ZonedDateTimeDbConverter::class)
    val sunrise: ZonedDateTime,
    //@TypeConverters(ZonedDateTimeDbConverter::class)
    val sunset: ZonedDateTime
)

data class DescriptionDb(
    val short: String,
    val description: String,
    val icon: String
)

data class WindDb(
    val speed: Double?, // m/s
    val degrees: Double?
//    val direction: CardinalDirection?
)

data class VolumeDb(val mm: Int, val h: Int)