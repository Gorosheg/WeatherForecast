package com.first.citiesscreen.data

import com.first.common.model.City
import com.first.network.model.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Single

interface CitiesRepository {

    val cities: Observable<List<City>>

    fun isEmpty():Boolean

    fun addCity(city: City)

    fun isCityExist(city: City):Boolean

    fun loadWeather(city: City): Single<WeatherResponse>

    fun removeCity(city: City)

    fun isFirstLaunch(): Boolean?

}