package com.first.weatherforecast.model

import com.google.gson.annotations.SerializedName

private const val ABSOLUTE_ZERO = 273.15
private const val MERCURY = 0.750063755419211

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
        get() = weather.degree.celsius

    val tempMax: Int
        get() = weather.tempMax.celsius

    val tempMin: Int
        get() = weather.tempMin.celsius

    val feelsLike: Int
        get() = weather.feelsLike.celsius

    val humidity: Int
        get() = weather.humidity

    val pressure: Int
        get() = weather.pressure.millimetrs


    val skyCondition: SkyCondition
        get() = SkyCondition.buildSkyCondition(sky.skyCondition)

    val skyImage: SkyImage
        get() = SkyImage.buildSkyImage(sky.skyCondition)


    private val Double.celsius: Int
        get() = (this - ABSOLUTE_ZERO).toInt()

    private val Int.millimetrs: Int
        get() = (this * MERCURY).toInt()
}

