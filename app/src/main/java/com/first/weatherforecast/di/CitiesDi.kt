package com.first.weatherforecast.di

import com.first.weatherforecast.data.CitiesRepository
import com.first.weatherforecast.data.CitiesRepositoryImpl
import com.first.weatherforecast.datasource.database.CitiesDatabase
import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource
import com.first.weatherforecast.domain.CitiesInteractor
import com.first.weatherforecast.domain.CitiesInteractorImpl

class CitiesDi(private val database: CitiesDatabase) {

    val interactor: CitiesInteractor
        get() = CitiesInteractorImpl(repository)

    private val repository: CitiesRepository
        get() = CitiesRepositoryImpl(datasource)

    private val datasource: CitiesDatabaseDatasource
        get() = CitiesDatabaseDatasource(database.cityDao)

}