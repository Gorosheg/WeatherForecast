package com.first.feature.weatherScreen.dI

import com.first.common.WeatherNavigator
import com.first.common.model.City
import com.first.dataSource.database.CitiesDatabaseDatasource
import com.first.dataSource.network.WeatherDatasource
import com.first.feature.weatherScreen.data.WeatherRepository
import com.first.feature.weatherScreen.data.WeatherRepositoryImpl
import com.first.feature.weatherScreen.domain.WeatherInteractor
import com.first.feature.weatherScreen.domain.WeatherInteractorImpl
import com.first.feature.weatherScreen.presentation.WeatherViewModelFactory

class WeatherDi(
    private val networkDataSource: WeatherDatasource,
    private val datasource: CitiesDatabaseDatasource,
    val weatherNavigator: WeatherNavigator
) {

    private val interactor: WeatherInteractor
        get() = WeatherInteractorImpl(repository)

    private val repository: WeatherRepository
        get() = WeatherRepositoryImpl(networkDataSource, datasource)

    internal fun getViewModelFactory(city: City): WeatherViewModelFactory {
        return WeatherViewModelFactory(interactor, city)
    }

    companion object {

        lateinit var instance: WeatherDi

    }

}