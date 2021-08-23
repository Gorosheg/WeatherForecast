package com.first.weatherforecast.model

import com.google.gson.annotations.SerializedName

class SkyConditions(
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