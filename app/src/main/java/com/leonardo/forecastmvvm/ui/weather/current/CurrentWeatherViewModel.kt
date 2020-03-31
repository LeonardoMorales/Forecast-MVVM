package com.leonardo.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.leonardo.forecastmvvm.data.repository.ForecastRepository
import com.leonardo.forecastmvvm.util.UnitSystem
import com.leonardo.forecastmvvm.util.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred{
        forecastRepository.getCurrentWeather()
    }
}
