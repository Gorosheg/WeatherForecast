package com.first.weatherforecast.common.model

import java.io.Serializable

data class City(// todo replace with Coordinates(lat, lon)
    val latitude: Double? = null,
    val longitude: Double? = null,
    val name: String? = null
) : Serializable

