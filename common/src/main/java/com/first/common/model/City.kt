package com.first.common.model

import java.io.Serializable

data class City(
    val coordinates: Coordinates? = null,
    val name: String? = null,
    var favorite: Boolean = false
) : Serializable
