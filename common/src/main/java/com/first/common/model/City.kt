package com.first.common.model

import java.io.Serializable

data class City(
    val coordinates: Coordinates? = null,
    val name: String? = null,
    val favorite: Boolean
) : Serializable
