package com.first.weatherScreen.dI

import com.first.common.WeatherNavigator
import com.first.common.model.City
import com.first.database.CitiesDatabaseDatasource
import com.first.network.WeatherDatasource
import com.first.weatherScreen.data.WeatherRepository
import com.first.weatherScreen.data.WeatherRepositoryImpl
import com.first.weatherScreen.domain.WeatherInteractor
import com.first.weatherScreen.domain.WeatherInteractorImpl
import com.first.weatherScreen.presentation.WeatherViewModelFactory

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