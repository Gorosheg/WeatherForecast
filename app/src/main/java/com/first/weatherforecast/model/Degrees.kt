package com.first.weatherforecast.model

import com.google.gson.annotations.SerializedName

class Degrees(
    @SerializedName("temp")
    val degree: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("humidity")
    val humidity: Int
)