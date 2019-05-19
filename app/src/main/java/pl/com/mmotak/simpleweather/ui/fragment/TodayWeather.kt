package pl.com.mmotak.simpleweather.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import pl.com.mmotak.simpleweather.R
import pl.com.mmotak.simpleweather.databinding.TodayWeatherFragmentBinding
import pl.com.mmotak.simpleweather.ui.factory.ViewModelFactory


class TodayWeather : Fragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory by instance<ViewModelFactory>()

    private lateinit var viewModel: TodayWeatherViewModel
    private var bindings: TodayWeatherFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindings = DataBindingUtil.inflate<TodayWeatherFragmentBinding>(inflater, R.layout.today_weather_fragment, container, false)
        return bindings?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayWeatherViewModel::class.java)
        bindings?.let {
            it.lifecycleOwner = viewLifecycleOwner
            it.vm = viewModel
        }

//        viewModel.weather.observe(this, Observer { weather ->
//            weather?.let { w ->
//                (activity as AppCompatActivity).supportActionBar?.let {
//                    Log.d("Weather", "" + w)
//                    it.title = w.locationName
//                    it.subtitle = w.dateTime
//                }
//            }
//        })
    }

}
