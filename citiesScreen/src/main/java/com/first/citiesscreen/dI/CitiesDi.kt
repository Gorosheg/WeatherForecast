package com.first.citiesscreen.dI

import com.first.citiesscreen.data.CitiesRepository
import com.first.citiesscreen.data.CitiesRepositoryImpl
import com.first.citiesscreen.domain.CitiesInteractor
import com.first.citiesscreen.domain.CitiesInteractorImpl
import com.first.citiesscreen.presentation.CitiesViewModelFactory
import com.first.common.Navigator
import com.first.database.CitiesDatabaseDatasource
import com.first.network.WeatherDatasource
import com.first.sharedpreference.PreferenceDatasource

class CitiesDi(
    private val datasource: CitiesDatabaseDatasource,
    private val networkDataSource: WeatherDatasource,
    private val preferenceDatasource: PreferenceDatasource,
    val navigator: Navigator
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