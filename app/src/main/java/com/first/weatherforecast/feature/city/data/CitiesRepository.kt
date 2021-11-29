package com.first.weatherforecast.feature.city.data

import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Single

interface CitiesRepository {

    val cities: Observable<List<City>>

    fun addCity(city: City)

    fun loadWeather(city: City): Single<WeatherResponse>

    fun removeCity(city: City)

}