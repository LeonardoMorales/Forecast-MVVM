package com.leonardo.forecastmvvm.ui.weather.current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.leonardo.forecastmvvm.R
import com.leonardo.forecastmvvm.data.api.ConnectivityInterceptorImpl
import com.leonardo.forecastmvvm.data.api.WeatherApiService
import com.leonardo.forecastmvvm.data.api.WeatherNetworkDataSource
import com.leonardo.forecastmvvm.data.api.WeatherNetworkDataSourceImpl
import com.leonardo.forecastmvvm.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val TAG: String = "AppDebug"

    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CurrentWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        suscribeObservers()

    }

    private fun suscribeObservers() = launch {
        val currentWeather = viewModel.weather.await()

        currentWeather.observe(viewLifecycleOwner, Observer {
            it?.let {
                textView.text = it.toString()
            }
        })
    }

}
