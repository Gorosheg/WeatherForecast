package com.first.citiesscreen.domain

import com.first.common.model.City
import com.first.common.model.Weather
import io.reactivex.Observable
import io.reactivex.Single

interface CitiesInteractor {

    val cities: Observable<List<City>>

    val isNoItems: Boolean

    fun addCity(city: City)

    fun isCityExist(city: City): Boolean

    fun loadWeather(city: City): Single<Weather>

    fun removeCity(city: City)

    val isFirstLaunch: Boolean


}