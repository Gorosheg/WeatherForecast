package com.first.weatherforecast.feature.city.domain

import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Single

interface CitiesInteractor {

    val cities: Observable<List<City>>

    fun addCity(city: City)

    fun isCityExist(city: City): Boolean

    fun loadWeather(city: City): Single<WeatherResponse>

    fun removeCity(city: City)

    fun isFirstLaunch(): Boolean?


}