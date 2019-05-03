package pl.com.mmotak.simpleweather.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import pl.com.mmotak.simpleweather.R

class DetailsWeather : Fragment() {

    companion object {
        fun newInstance() = DetailsWeather()
    }

    private lateinit var viewModel: DetailsWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
