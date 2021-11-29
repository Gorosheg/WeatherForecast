package com.first.weatherforecast.feature.weather.dI

import com.first.weatherforecast.datasource.database.NetworkDataSource
import com.first.weatherforecast.feature.weather.data.WeatherRepository
import com.first.weatherforecast.feature.weather.data.WeatherRepositoryImpl
import com.first.weatherforecast.feature.weather.domain.WeatherInteractor
import com.first.weatherforecast.feature.weather.domain.WeatherInteractorImpl

class WeatherDi(private val networkDataSource: NetworkDataSource) {

    val interactor: WeatherInteractor
        get() = WeatherInteractorImpl(repository)

    private val repository: WeatherRepository
        get() = WeatherRepositoryImpl(networkDataSource)

}