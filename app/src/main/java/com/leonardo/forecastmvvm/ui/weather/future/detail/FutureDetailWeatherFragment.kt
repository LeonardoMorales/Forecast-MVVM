package com.leonardo.forecastmvvm.ui.weather.future.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.leonardo.forecastmvvm.R

class FutureDetailWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            FutureDetailWeatherFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(FutureDetailWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_detail_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
