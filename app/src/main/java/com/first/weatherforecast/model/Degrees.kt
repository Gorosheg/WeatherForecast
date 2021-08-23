package com.first.weatherforecast.model

import com.google.gson.annotations.SerializedName

class Degrees(
    @SerializedName("temp")
    val degree: Double
)