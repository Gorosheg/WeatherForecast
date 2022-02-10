package com.first.feature.citiesscreen.dI

import com.first.feature.citiesscreen.data.CitiesRepository
import com.first.feature.citiesscreen.data.CitiesRepositoryImpl
import com.first.feature.citiesscreen.domain.CitiesInteractor
import com.first.feature.citiesscreen.domain.CitiesInteractorImpl
import com.first.feature.citiesscreen.presentation.CitiesViewModelFactory
import com.first.common.CityNavigator
import com.first.dataSource.database.CitiesDatabaseDatasource
import com.first.dataSource.network.WeatherDatasource
import com.first.dataSource.sharedpreference.PreferenceDatasource

class CitiesDi(
    private val datasource: CitiesDatabaseDatasource,
    private val networkDataSource: WeatherDatasource,
    private val preferenceDatasource: PreferenceDatasource,
    val cityNavigator: CityNavigator
) {

    private val interactor: CitiesInteractor
        get() = CitiesInteractorImpl(repository)

    private val repository: CitiesRepository
        get() = CitiesRepositoryImpl(datasource, networkDataSource, preferenceDatasource)

    fun getViewModelFactory(): CitiesViewModelFactory { // Вызываем из Activity
        return CitiesViewModelFactory(interactor) // Создает ViewModelFactory
    }

    companion object {

        lateinit var instance: CitiesDi

    }

}