package com.leonardo.forecastmvvm.data.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leonardo.forecastmvvm.data.model.CURRENT_WEATHER_ID
import com.leonardo.forecastmvvm.data.model.CurrentWeatherEntry

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)   // The conflic hapens when in the database exist two or more entities with the same id, in this case in the mayority of times will hapen because we use the same id to storage the current weater in our database
    fun upsert(currentWeatherEntry: CurrentWeatherEntry)

    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
}