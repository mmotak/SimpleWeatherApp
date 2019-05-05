package pl.com.mmotak.simpleweather.openweather.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import pl.com.mmotak.simpleweather.openweather.network.model.OpenWeatherWeb
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&mode=xml&appid=b6907d289e10d714a6e88b30761fae22
// https://api.openweathermap.org/data/2.5/forecast?appid=e72ca729af228beabd5d20e3b7749713&q=London&lang=pl
// https://api.openweathermap.org/data/2.5/weather?appid=e72ca729af228beabd5d20e3b7749713&q=London&lang=pl
// https://openweathermap.org/img/w/04d.png

interface OpenWeatherApi {
    companion object Const {
        private const val OPEN_WEATHER_API = "https://api.openweathermap.org/data/2.5/"
        private const val OPEN_WEATHER_IMAGE_API = "https://openweathermap.org/img/w/"

        private const val API_KEY_VALUE = "e72ca729af228beabd5d20e3b7749713"
        private const val API_KEY_ID = "appid"

        operator fun invoke() : OpenWeatherApi {
            val apiKeyInterceptor = Interceptor {
                val newUrl = it.request().url().newBuilder().addQueryParameter(
                    API_KEY_ID,
                    API_KEY_VALUE
                ).build()
                val newRequest = it.request().newBuilder().url(newUrl).build()
                return@Interceptor it.proceed(newRequest)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(OPEN_WEATHER_API)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(OpenWeatherApi::class.java)
        }
    }

    @GET("weather")
    fun getWeather(@Query("q") cityName: String, @Query("lang") language: String = "en") : Deferred<OpenWeatherWeb>
}