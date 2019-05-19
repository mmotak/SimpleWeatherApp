package pl.com.mmotak.simpleweather

import android.app.Application
import android.view.View
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import pl.com.mmotak.simpleweather.data.db.WeatherDatabase
import pl.com.mmotak.simpleweather.data.network.WeatherNetworkProvider
import pl.com.mmotak.simpleweather.data.openweather.network.OpenWeatherNetworkProvider
import pl.com.mmotak.simpleweather.data.repo.WeatherRepository
import pl.com.mmotak.simpleweather.data.repo.WeatherRepositoryImpl
import pl.com.mmotak.simpleweather.ui.factory.ViewModelFactory


class SimpleWeatherApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@SimpleWeatherApplication))

        // database
        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().currentWeatherDao() }

        // openweather - network
        bind<WeatherNetworkProvider>() with singleton { OpenWeatherNetworkProvider() }

        // repo
        bind<WeatherRepository>() with singleton { WeatherRepositoryImpl(instance(), instance()) }

        // view model factory
        bind<ViewModelFactory>() with singleton { ViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}