package pl.com.mmotak.simpleweather.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.Deferred
import pl.com.mmotak.simpleweather.data.db.model.WeatherDb

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherDb)

    @Query("select * from weather_db_table where id like :name")
    suspend fun loadCurrentWeather(name : String) : WeatherDb?
}