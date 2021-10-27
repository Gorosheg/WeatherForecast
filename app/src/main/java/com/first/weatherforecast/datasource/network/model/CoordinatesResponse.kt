package com.first.weatherforecast.datasource.network.model

import com.google.gson.annotations.SerializedName

class CoordinatesResponse (
    @SerializedName("lat")
    val latitude: Double,

    @SerializedName("lon")
    val longitude: Double
)