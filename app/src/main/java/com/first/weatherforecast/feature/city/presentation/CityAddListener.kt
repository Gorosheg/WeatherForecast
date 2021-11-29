package com.first.weatherforecast.feature.city.presentation

interface CityAddListener {

    fun onCityAdd(latitude: Double? = null, longitude: Double? = null, name: String? = null)

}