package com.first.weatherforecast.common.model

import java.io.Serializable

data class City(
    val coordinates: Coordinates? = null,
    val name: String? = null
) : Serializable
