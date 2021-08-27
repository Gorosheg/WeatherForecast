package com.first.weatherforecast.model

import java.io.Serializable

class City(
    val latitude: Double,
    val longitude: Double,
    val name: String
) : Serializable