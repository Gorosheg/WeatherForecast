package com.first.dataSource.network.model

import com.google.gson.annotations.SerializedName

internal class CoordinatesResponse (
    @SerializedName("lat")
    val latitude: Double,

    @SerializedName("lon")
    val longitude: Double
)