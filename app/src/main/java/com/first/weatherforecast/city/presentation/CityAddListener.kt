package com.first.weatherforecast.city.presentation

interface CityAddListener {

    fun onCityAdd(latitude: Double? = null, longitude: Double? = null, name: String? = null)

}