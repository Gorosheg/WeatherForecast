package com.first.weatherforecast.citiesDomain

import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Observable
import io.reactivex.Single

interface CitiesInteractor {

    val cities: Observable<List<City>>

    fun addCity(city: City)

    fun loadWeather(city: City): Single<WeatherResponse>

    fun removeCity(city: City)


}