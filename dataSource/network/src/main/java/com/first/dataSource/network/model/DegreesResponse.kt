package com.first.dataSource.network.model

import com.google.gson.annotations.SerializedName

internal class DegreesResponse(
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