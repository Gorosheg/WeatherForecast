package com.first.weatherforecast.datasource.network.model

import com.first.weatherforecast.model.SkyCondition
import com.first.weatherforecast.model.SkyImage
import com.google.gson.annotations.SerializedName

private const val ABSOLUTE_ZERO = 273.15
private const val MERCURY = 0.750063755419211

class WeatherResponse(
    @SerializedName("main")
    private val weather: DegreesResponse,

    @SerializedName("weather")
    private val skies: List<SkyConditionsResponse>,

    @SerializedName("name")
    val city: String,

    @SerializedName("coord")
    val coordinates: CoordinatesResponse
) {

    val latitude: Double
        get() = coordinates.latitude

    val longitude: Double
        get() = coordinates.longitude

    private val sky: SkyConditionsResponse
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
        get() = weather.pressure.millimeters


    val skyCondition: SkyCondition
        get() = SkyCondition.buildSkyCondition(sky.skyCondition)

    val skyImage: SkyImage
        get() = SkyImage.buildSkyImage(sky.skyCondition)


    private val Double.celsius: Int
        get() = (this - ABSOLUTE_ZERO).toInt()

    private val Int.millimeters: Int
        get() = (this * MERCURY).toInt()
}
