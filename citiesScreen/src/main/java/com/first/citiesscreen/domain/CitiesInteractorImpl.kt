package com.first.citiesscreen.domain

import com.first.citiesscreen.data.CitiesRepository
import com.first.common.model.City
import com.first.network.model.WeatherResponse
import io.reactivex.Observable
import io.reactivex.Single

class CitiesInteractorImpl(private val repository: CitiesRepository) : CitiesInteractor {

    override val cities: Observable<List<City>>
        get() = repository.cities

    override fun isEmpty(): Boolean {
        return repository.isEmpty()
    }

    override fun addCity(city: City) {
        repository.addCity(city)
    }

    override fun isCityExist(city: City): Boolean {
        return repository.isCityExist(city)
    }

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return repository.loadWeather(city)
    }

    override fun removeCity(city: City) {
        repository.removeCity(city)
    }

    override fun isFirstLaunch(): Boolean? {
        return repository.isFirstLaunch()
    }
}