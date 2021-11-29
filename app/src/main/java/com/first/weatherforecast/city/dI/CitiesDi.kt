package com.first.weatherforecast.city.dI

import com.first.weatherforecast.city.data.CitiesRepository
import com.first.weatherforecast.city.data.CitiesRepositoryImpl
import com.first.weatherforecast.city.domain.CitiesInteractor
import com.first.weatherforecast.city.domain.CitiesInteractorImpl
import com.first.weatherforecast.datasource.database.CitiesDatabase
import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource
import com.first.weatherforecast.datasource.database.NetworkDataSource

class CitiesDi(private val database: CitiesDatabase) {

    val interactor: CitiesInteractor
        get() = CitiesInteractorImpl(repository)

    private val repository: CitiesRepository
        get() = CitiesRepositoryImpl(datasource, networkDataSource)

    private val datasource: CitiesDatabaseDatasource
        get() = CitiesDatabaseDatasource(database.cityDao)

    private val networkDataSource: NetworkDataSource
        get() = NetworkDataSource()

}