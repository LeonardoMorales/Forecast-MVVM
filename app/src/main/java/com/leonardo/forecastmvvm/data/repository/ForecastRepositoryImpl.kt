package com.leonardo.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.leonardo.forecastmvvm.data.api.WeatherNetworkDataSource
import com.leonardo.forecastmvvm.data.api.network_responses.CurrentWeatherResponse
import com.leonardo.forecastmvvm.data.model.CurrentWeatherEntry
import com.leonardo.forecastmvvm.data.persistance.CurrentWeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository{

    init {
        weatherNetworkDataSource.downloadCurrentWeather.observeForever {newCurrentWeather ->
            persistFethCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext currentWeatherDao.getCurrentWeather()
        }
    }

    private fun persistFethCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData() {
        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1))){
            fetchCurrentWeather()
        }
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
            "Los Angeles",
            "m"  // m, s, f
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thiryMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thiryMinutesAgo)
    }
}