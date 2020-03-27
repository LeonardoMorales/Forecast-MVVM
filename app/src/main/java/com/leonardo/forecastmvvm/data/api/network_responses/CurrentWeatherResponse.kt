package com.leonardo.forecastmvvm.data.api.network_responses


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.leonardo.forecastmvvm.data.model.CurrentWeatherEntry
import com.leonardo.forecastmvvm.data.model.Location
import com.leonardo.forecastmvvm.data.model.Request

class CurrentWeatherResponse(
    @SerializedName("current")
    @Expose
    val currentWeatherEntry: CurrentWeatherEntry,

    @SerializedName("location")
    @Expose
    val location: Location,

    @SerializedName("request")
    @Expose
    val request: Request
)