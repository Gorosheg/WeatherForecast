package com.first.citiesscreen.domain

import com.first.common.model.City
import com.first.network.model.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Single

interface CitiesInteractor {

    val cities: Observable<List<City>>

    fun isEmpty():Boolean

    fun addCity(city: City)

    fun isCityExist(city: City): Boolean

    fun loadWeather(city: City): Single<WeatherResponse>

    fun removeCity(city: City)

    fun isFirstLaunch(): Boolean?


}