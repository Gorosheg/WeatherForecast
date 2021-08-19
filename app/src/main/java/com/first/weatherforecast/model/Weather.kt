package com.first.weatherforecast

import com.google.gson.annotations.SerializedName

private const val ABSOLUTE_ZERO = 273.15

class Weather(
    @SerializedName("main")
    private val weather: WeatherMain
) {
    val degree: Int
        get() = (weather.degree - ABSOLUTE_ZERO).toInt()
}

class WeatherMain(
    @SerializedName("temp")
    val degree: Double
)