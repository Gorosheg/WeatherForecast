package com.first.citiesscreen.data

import com.first.database.CitiesDatabaseDatasource
import com.first.database.NetworkDataSource
import com.first.network.model.WeatherResponse
import com.first.common.model.City
import com.first.sharedpreference.PreferenceDatasource
import io.reactivex.Observable
import io.reactivex.Single

class CitiesRepositoryImpl(
    private val database: CitiesDatabaseDatasource,
    private val networkDataSource: NetworkDataSource,
    private val PreferenceDatasource: com.first.sharedpreference.PreferenceDatasource
) : CitiesRepository {

    override val cities: Observable<List<City>>
        get() = this.database.getAllCities()

    override fun addCity(city: City) {
        this.database.addCity(city)
    }

    override fun isCityExist(city: City): Boolean {
        return this.database.isCityExist(city)
    }

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return networkDataSource.loadingWeather(city)
    }

    override fun removeCity(city: City) {
        this.database.deleteCity(city)
    }

    override fun isFirstLaunch(): Boolean? {
        return PreferenceDatasource.isFirstLaunch

    }
}