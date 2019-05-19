package pl.com.mmotak.simpleweather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.com.mmotak.simpleweather.data.model.Weather

@Database(
    entities = [Weather::class],
    version = 1,
    exportSchema = false)
@TypeConverters(
    DescriptionsDbConverter::class,
    VolumeDbConverter::class,
    WindDbConverter::class,
    LocalDateTimeDbConverter::class,
    ZonedDateTimeDbConverter::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao() : CurrentWeatherDao

    companion object {
        @Volatile
        private var DB_INSTANCE : WeatherDatabase? = null
        private val LOCK = Object()

        operator fun invoke(context : Context) = DB_INSTANCE ?:
                synchronized(LOCK) {
                    DB_INSTANCE ?: createDB(context).also{ DB_INSTANCE = it}
                }

        private fun createDB(context: Context): WeatherDatabase =
            Room.databaseBuilder(
                context.applicationContext.applicationContext,
                WeatherDatabase::class.java,
                "weather.db")
                .build()
    }

}