package com.leonardo.forecastmvvm.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(
    val cloudcover: Int,

    val feelslike: Int,

    val humidity: Int,

    @SerializedName("is_day")
    val isDay: String,

    @SerializedName("observation_time")
    val observationTime: String,

    val precip: Int,

    val pressure: Int,

    val temperature: Double,

    @SerializedName("uv_index")
    val uvIndex: Int,

    val visibility: Int,

    @SerializedName("weather_code")
    val weatherCode: Int,

    @Ignore
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>? = null,

    @Ignore
    @SerializedName("weather_icons")
    val weatherIcons: List<String>? = null,

    @SerializedName("wind_degree")
    val windDegree: Int,

    @SerializedName("wind_dir")
    val windDir: String,

    @SerializedName("wind_speed")
    val windSpeed: Int
) {
    constructor(
        cloudcover: Int,
        feelslike: Int,
        humidity: Int,
        isDay: String,
        observationTime: String,
        precip: Int,
        pressure: Int,
        temperature: Double,
        uvIndex: Int,
        visibility: Int,
        weatherCode: Int,
        windDegree: Int,
        windDir: String,
        windSpeed: Int
    ) : this(
        cloudcover,
        feelslike,
        humidity,
        isDay,
        observationTime,
        precip,
        pressure,
        temperature,
        uvIndex,
        visibility,
        weatherCode,
        arrayListOf<String>(),
        arrayListOf<String>(),
        windDegree,
        windDir,
        windSpeed
    )  // We need put this secondary constructur because room need to know other instantiation of our class without the ignorance params

    @PrimaryKey(autoGenerate = false)   // In this case we use false because it's only one currentweather, we don't need to storage multiple rows
    var id: Int = CURRENT_WEATHER_ID
}