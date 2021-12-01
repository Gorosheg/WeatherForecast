package com.first.weatherforecast.feature.city.dI

import com.first.weatherforecast.datasource.sharedPreference.PreferenceDatasource
import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource
import com.first.weatherforecast.datasource.database.NetworkDataSource
import com.first.weatherforecast.feature.city.data.CitiesRepository
import com.first.weatherforecast.feature.city.data.CitiesRepositoryImpl
import com.first.weatherforecast.feature.city.domain.CitiesInteractor
import com.first.weatherforecast.feature.city.domain.CitiesInteractorImpl

class CitiesDi(
    private val datasource: CitiesDatabaseDatasource,
    private val networkDataSource: NetworkDataSource,
    private val isFirstLaunch: PreferenceDatasource
) {

    val interactor: CitiesInteractor
        get() = CitiesInteractorImpl(repository)

    private val repository: CitiesRepository
        get() = CitiesRepositoryImpl(datasource, networkDataSource, isFirstLaunch)

}