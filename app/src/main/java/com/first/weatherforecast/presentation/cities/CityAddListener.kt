package com.first.weatherforecast.presentation.cities

interface CityAddListener {

    fun onCityAdd(latitude: Double? = null, longitude: Double? = null, name: String? = null)

}