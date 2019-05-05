package pl.com.mmotak.simpleweather.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.today_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import pl.com.mmotak.simpleweather.R
import pl.com.mmotak.simpleweather.openweather.network.model.Lang
import pl.com.mmotak.simpleweather.openweather.network.OpenWeatherApi

class TodayWeather : Fragment() {

    companion object {
        fun newInstance() = TodayWeather()
    }

    private lateinit var viewModel: TodayWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodayWeatherViewModel::class.java)

        viewModel.weather.observe(this, Observer {
            textView.text = it.toString()
        })
        // TODO: Use the ViewModel

        //val backend = OpenWeatherApi()

//        GlobalScope.launch(Dispatchers.Main) {
//            try {
//                val result = backend.getWeather("Gliwice", Lang.Polish.code).await()
//                textView.text = result.toString()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
    }

}
