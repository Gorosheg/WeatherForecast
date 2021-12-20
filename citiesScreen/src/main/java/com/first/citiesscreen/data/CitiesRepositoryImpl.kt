package com.first.citiesscreen.data

import com.first.common.model.City
import com.first.common.model.Weather
import com.first.database.CitiesDatabaseDatasource
import com.first.network.WeatherDatasource
import com.first.sharedpreference.PreferenceDatasource
import io.reactivex.Observable
import io.reactivex.Single

internal class CitiesRepositoryImpl(
    private val database: CitiesDatabaseDatasource,
    private val networkDataSource: WeatherDatasource,
    private val PreferenceDatasource: PreferenceDatasource
) : CitiesRepository {

    override val cities: Observable<List<City>>
        get() = this.database.getAllCities()

    override fun isEmpty(): Boolean {
        return this.database.isEmpty()
    }

    override fun addCity(city: City) {
        this.database.addCity(city)
    }

    override fun isCityExist(city: City): Boolean {
        return this.database.isCityExist(city)
    }

    override fun loadWeather(city: City): Single<Weather> {
        return networkDataSource.loadingWeather(city)
    }

    override fun removeCity(city: City) {
        this.database.deleteCity(city)
    }

    override fun isFirstLaunch(): Boolean? {
        return PreferenceDatasource.isFirstLaunch

    }
}