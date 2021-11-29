package com.first.weatherforecast.data

import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource
import com.first.weatherforecast.datasource.database.NetworkDataSource
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Observable
import io.reactivex.Single

class CitiesRepositoryImpl(private val database: CitiesDatabaseDatasource) : CitiesRepository {

    override val cities: Observable<List<City>>
    get() = this.database.getAllCities()

    override fun addCity(city: City) {
        this.database.addCity(city)
    }

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return NetworkDataSource().loadingWeather(city)
    }

    override fun removeCity(city: City) {
        this.database.deleteCity(city)
    }
}