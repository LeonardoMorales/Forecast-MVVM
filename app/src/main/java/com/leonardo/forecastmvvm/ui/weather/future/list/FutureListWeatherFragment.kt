package com.leonardo.forecastmvvm.ui.weather.future.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.leonardo.forecastmvvm.R

class FutureListWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            FutureListWeatherFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(FutureListWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_list_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
