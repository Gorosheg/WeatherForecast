package com.first.weatherforecast.ui.model

import java.io.Serializable

class City(
    val latitude: Double,
    val longitude: Double,
    var name: String
) : Serializable

