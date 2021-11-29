package com.first.weatherforecast.citiesPresentation.cities

interface CityAddListener {

    fun onCityAdd(latitude: Double? = null, longitude: Double? = null, name: String? = null)

}