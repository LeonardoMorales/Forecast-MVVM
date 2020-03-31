package com.leonardo.forecastmvvm.data.api

import androidx.lifecycle.LiveData
import com.leonardo.forecastmvvm.data.api.network_responses.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String, unit: String)
}
