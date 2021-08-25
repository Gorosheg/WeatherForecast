package com.first.weatherforecast.model

import com.google.gson.annotations.SerializedName

private const val ABSOLUTE_ZERO = 273.15

class Weather(
    @SerializedName("main")
    private val weather: Degrees,

    @SerializedName("weather")
    private val skies: List<SkyConditions>,

    @SerializedName("name")
    val city: String
) {

    private val sky: SkyConditions
        get() = skies.first()

    val degree: Int
        get() = (weather.degree - ABSOLUTE_ZERO).toInt()

    val tempMax: Int
        get() = (weather.tempMax - ABSOLUTE_ZERO).toInt()

    val tempMin: Int
        get() = (weather.tempMin - ABSOLUTE_ZERO).toInt()

    val feelsLike: Int
        get() = (weather.feelsLike - ABSOLUTE_ZERO).toInt()

    val skyCondition: SkyCondition
        get() = SkyCondition.buildSkyCondition(sky.skyCondition)

    val skyImage: SkyImage
        get() = SkyImage.buildSkyImage(sky.skyCondition)

}

