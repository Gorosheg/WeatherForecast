package com.first.weatherforecast.feature.city.data

import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource
import com.first.weatherforecast.datasource.database.NetworkDataSource
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.datasource.sharedPreference.PreferenceDatasource
import io.reactivex.Observable
import io.reactivex.Single

class CitiesRepositoryImpl(
    private val database: CitiesDatabaseDatasource,
    private val networkDataSource: NetworkDataSource,
    private val PreferenceDatasource: PreferenceDatasource
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