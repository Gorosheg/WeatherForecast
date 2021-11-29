package com.first.weatherforecast.citiesDomain

import com.first.weatherforecast.citiesData.CitiesRepository
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Observable
import io.reactivex.Single

class CitiesInteractorImpl(private val repository: CitiesRepository) : CitiesInteractor {

    override val cities: Observable<List<City>>
        get() = repository.cities

    override fun addCity(city: City) {
        repository.addCity(city)
    }

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return repository.loadWeather(city)
    }

    override fun removeCity(city: City) {
        repository.removeCity(city)
    }
}