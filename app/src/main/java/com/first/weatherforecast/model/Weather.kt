package com.first.weatherforecast.model

import com.google.gson.annotations.SerializedName

private const val ABSOLUTE_ZERO = 273.15

class Weather(
    @SerializedName("main")
    private val weather: Degrees,

    @SerializedName("weather")
    private val sky: List<SkyConditions>
) {

    val degree: Int
        get() = (weather.degree - ABSOLUTE_ZERO).toInt()

    val skyCondition: SkyCondition
        get() = SkyCondition.stringOf(sky.first().skyCondition)

}