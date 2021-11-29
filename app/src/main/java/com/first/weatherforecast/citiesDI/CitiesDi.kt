package com.first.weatherforecast.citiesDI

import com.first.weatherforecast.citiesData.CitiesRepository
import com.first.weatherforecast.citiesData.CitiesRepositoryImpl
import com.first.weatherforecast.citiesDomain.CitiesInteractor
import com.first.weatherforecast.citiesDomain.CitiesInteractorImpl
import com.first.weatherforecast.datasource.database.CitiesDatabase
import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource

class CitiesDi(private val database: CitiesDatabase) {

    val interactor: CitiesInteractor
        get() = CitiesInteractorImpl(repository)

    private val repository: CitiesRepository
        get() = CitiesRepositoryImpl(datasource)

    private val datasource: CitiesDatabaseDatasource
        get() = CitiesDatabaseDatasource(database.cityDao)

}