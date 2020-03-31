package com.leonardo.forecastmvvm.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leonardo.forecastmvvm.data.api.network_responses.CurrentWeatherResponse
import com.leonardo.forecastmvvm.util.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val weatherApiService: WeatherApiService
) : WeatherNetworkDataSource {

    private val TAG: String = "AppDebug"

    private val _downloadCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, units: String) {
        try {
            val fetchCurrentWeather = weatherApiService
                .getCurrentWeather(location, units)
                .await()
            _downloadCurrentWeather.postValue(fetchCurrentWeather)
        }
        catch(e: NoConnectivityException) {
            Log.e(TAG, "No internet connection", e)
        }
    }
}