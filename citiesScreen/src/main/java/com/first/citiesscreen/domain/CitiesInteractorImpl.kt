package com.first.citiesscreen.domain

import com.first.citiesscreen.data.CitiesRepository
import com.first.common.model.City
import com.first.common.model.Weather
import io.reactivex.Observable
import io.reactivex.Single

internal class CitiesInteractorImpl(private val repository: CitiesRepository) : CitiesInteractor {

    override val cities: Observable<List<City>>
        get() = repository.cities

    override val isNoItems: Boolean
        get() = repository.isNoItems

    override val isFirstLaunch: Boolean
        get() = repository.isFirstLaunch

    override fun addCity(city: City) {
        repository.addCity(city)
    }

    override fun isCityExist(city: City): Boolean {
        return repository.isCityExist(city)
    }

    override fun loadWeather(city: City): Single<Weather> {
        return repository.loadWeather(city)
    }

    override fun removeCity(city: City) {
        repository.removeCity(city)
    }

    override fun makeCityFavorite(city: City) {
        repository.makeCityFavorite(city)
    }
}