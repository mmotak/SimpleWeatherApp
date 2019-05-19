package pl.com.mmotak.simpleweather.data.db.converter

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import pl.com.mmotak.simpleweather.data.db.model.DescriptionDb
import pl.com.mmotak.simpleweather.data.db.model.VolumeDb
import pl.com.mmotak.simpleweather.data.db.model.WindDb

abstract class GSONConverer<T> {

    @TypeConverter
    fun toJson(item: T) : String = Gson().toJson(item)

    @TypeConverter
    fun fromJson(json: String) : T {
        val type = object : TypeToken<T>() { }.type
        Log.d("Weather","type ($type)")
        Log.d("Weather","json ($json)")
        return Gson().fromJson(json, type)
    }

    //inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)
}

class DescriptionDbConverter {
    @TypeConverter
    fun toJson(item: List<DescriptionDb>) : String = Gson().toJson(item)

    @TypeConverter
    fun fromJson(json: String) : List<DescriptionDb> {
        val type = object : TypeToken<List<DescriptionDb>>() { }.type
        Log.d("Weather","type ($type)")
        Log.d("Weather","json ($json)")
        return Gson().fromJson(json, type)
    }
}

class VolumeDbConverter {
    @TypeConverter
    fun toJson(item: VolumeDb) : String = Gson().toJson(item)

    @TypeConverter
    fun fromJson(json: String) : VolumeDb {
        Log.d("Weather","json ($json)")
        return Gson().fromJson(json, VolumeDb::class.java)
    }
}
class WindDbConverter {
    @TypeConverter
    fun toJson(item: WindDb) : String = Gson().toJson(item)

    @TypeConverter
    fun fromJson(json: String) : WindDb {
        Log.d("Weather","json ($json)")
        return Gson().fromJson(json, WindDb::class.java)
    }
}


class LocalDateTimeDbConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime): Long {
        return date.atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
    }
}

class ZonedDateTimeDbConverter {
    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    @TypeConverter
    fun toDate(value: String): ZonedDateTime = ZonedDateTime.parse(value, formatter)

    @TypeConverter
    fun toString(date: ZonedDateTime): String = date.format(formatter)
}