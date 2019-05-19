package pl.com.mmotak.simpleweather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.com.mmotak.simpleweather.data.model.Weather

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weather: Weather)

    @Query("select * from weather_table where id like :name")
    fun loadCurrentWeather(name : String) : LiveData<Weather>
}