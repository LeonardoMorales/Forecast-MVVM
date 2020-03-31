package com.leonardo.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.leonardo.forecastmvvm.data.model.CurrentWeatherEntry

interface ForecastRepository {

    suspend fun getCurrentWeather() : LiveData<CurrentWeatherEntry>

}