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
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    private val TAG: String = "AppDebug"

    companion object {
        fun newInstance() =
            CurrentWeatherFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService = WeatherApiService(ConnectivityInterceptorImpl(this.context!!))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadCurrentWeather.observe(viewLifecycleOwner, Observer {
            textView.text = it.currentWeatherEntry.weatherDescriptions?.get(0).toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            weatherNetworkDataSource.fetchCurrentWeather("London")
        }

    }

}
