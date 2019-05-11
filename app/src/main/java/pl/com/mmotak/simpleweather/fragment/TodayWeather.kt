package pl.com.mmotak.simpleweather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.com.mmotak.simpleweather.R
import pl.com.mmotak.simpleweather.databinding.TodayWeatherFragmentBinding


class TodayWeather : Fragment() {

    companion object {
        fun newInstance() = TodayWeather()
    }

    private lateinit var viewModel: TodayWeatherViewModel

    private var bindings: TodayWeatherFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindings = DataBindingUtil.inflate<TodayWeatherFragmentBinding>(inflater, R.layout.today_weather_fragment, container, false)
        return bindings?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodayWeatherViewModel::class.java)
        bindings?.let {
            it.lifecycleOwner = viewLifecycleOwner
            it.vm = viewModel
        }

        viewModel.weather.observe(this, Observer {weather ->
            (activity as AppCompatActivity).supportActionBar?.let {
                it.title = weather.location.name
                it.subtitle = weather.lastUpdate.toLocalDate().toString()
            }
        })
    }

}
