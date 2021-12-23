package com.first.citiesscreen.presentation

internal interface CityAddListener {

    fun onCityAdd(latitude: Double? = null, longitude: Double? = null, name: String? = null)

}