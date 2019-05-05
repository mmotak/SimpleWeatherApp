package pl.com.mmotak.simpleweather

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen


class SimpleWeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}