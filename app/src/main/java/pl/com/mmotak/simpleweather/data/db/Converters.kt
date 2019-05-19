package pl.com.mmotak.simpleweather.data.db

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import pl.com.mmotak.simpleweather.data.model.Description
import pl.com.mmotak.simpleweather.data.model.Volume
import pl.com.mmotak.simpleweather.data.model.Wind

abstract class GSONConverer<T> {

    @TypeConverter
    fun toJson(item: T) : String = Gson().toJson(item)

    @TypeConverter
    fun fromJson(json: String) : T {
        val type = object : TypeToken<T>() { }.type
        //Log.d("Weather","type ($type)")
        //Log.d("Weather","json ($json)")
        return Gson().fromJson(json, type)
    }

    //inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)
}



class DescriptionsDbConverter {
    private val gson = Gson()

    @TypeConverter
    fun toJson(list: List<Description>) : String = gson.toJson(list)

    @TypeConverter
    fun toList(json: String) : List<Description> {
        val listType = object : TypeToken<List<Description>>() { }.type
        //Log.d("Weather","type ($listType)")
        //Log.d("Weather","json ($json)")
        return gson.fromJson(json, listType)
    }
}

class VolumeDbConverter {
    private val gson = Gson()

    @TypeConverter
    fun toJson(volume: Volume) : String = gson.toJson(volume)

    @TypeConverter
    fun toVolume(json: String) : Volume = gson.fromJson(json, Volume::class.java)
}

class WindDbConverter {
    private val gson = Gson()

    @TypeConverter
    fun toJson(wind: Wind) : String = gson.toJson(wind)

    @TypeConverter
    fun toWind(json: String) : Wind = gson.fromJson(json, Wind::class.java)
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