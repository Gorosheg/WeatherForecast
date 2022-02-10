package com.first.dataSource.network.model

import com.google.gson.annotations.SerializedName

internal class SkyConditionsResponse(
    /**
     * Состояние неба.
     * Варианты:
     * Clear
     * Snow
     * Rain
     * Drizzle
     * Thunderstorm
     * Clouds
     * Mist
     * Smoke
     * Haze
     * Dust
     * Fog
     * Sand
     * Ash
     * Squall
     * Tornado
     */
    @SerializedName("main")
    val skyCondition: String
)