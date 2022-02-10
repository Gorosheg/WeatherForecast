package com.first.dataSource.network.model

import com.google.gson.annotations.SerializedName

internal class WindResponse(
    @SerializedName("speed")
    val speed: Double
)