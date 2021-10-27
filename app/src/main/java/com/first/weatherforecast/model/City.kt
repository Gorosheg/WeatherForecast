package com.first.weatherforecast.model

import java.io.Serializable

data class City(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val name: String? = null
) : Serializable

