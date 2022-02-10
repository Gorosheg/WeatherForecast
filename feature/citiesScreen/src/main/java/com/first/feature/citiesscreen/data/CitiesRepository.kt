package com.first.feature.citiesscreen.data

import com.first.common.model.City
import com.first.common.model.Weather
import io.reactivex.Observable
import io.reactivex.Single

internal interface CitiesRepository {

    val cities: Observable<List<City>>

    val isNoItems: Boolean

    val isFirstLaunch: Boolean

    fun addCity(city: City)

    fun isCityExist(city: City): Boolean

    fun loadWeather(city: City): Single<Weather>

    fun changeFavoriteState(city: City)

    fun removeCity(city: City)

}