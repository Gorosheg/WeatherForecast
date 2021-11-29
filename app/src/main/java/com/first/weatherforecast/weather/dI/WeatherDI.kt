package com.first.weatherforecast.weather.dI

import com.first.weatherforecast.datasource.database.NetworkDataSource
import com.first.weatherforecast.weather.data.WeatherRepository
import com.first.weatherforecast.weather.data.WeatherRepositoryImpl
import com.first.weatherforecast.weather.domain.WeatherInteractor
import com.first.weatherforecast.weather.domain.WeatherInteractorImpl

class WeatherDI () {

    val interactor: WeatherInteractor
        get() = WeatherInteractorImpl(repository)

    private val repository: WeatherRepository
        get() = WeatherRepositoryImpl(networkDataSource)

    private val networkDataSource: NetworkDataSource
        get() = NetworkDataSource()

}