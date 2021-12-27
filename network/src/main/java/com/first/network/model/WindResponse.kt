package com.first.network.model

import com.google.gson.annotations.SerializedName

internal class WindResponse(
    @SerializedName("speed")
    val speed: Double
)