package com.first.weatherforecast.feature.weather.dI

import com.first.common.model.City
import com.first.database.NetworkDataSource
import com.first.weatherforecast.feature.weather.data.WeatherRepository
import com.first.weatherforecast.feature.weather.data.WeatherRepositoryImpl
import com.first.weatherforecast.feature.weather.domain.WeatherInteractor
import com.first.weatherforecast.feature.weather.domain.WeatherInteractorImpl
import com.first.weatherforecast.feature.weather.presentation.WeatherViewModelFactory

class WeatherDi(private val networkDataSource: NetworkDataSource) {

    private val interactor: WeatherInteractor
        get() = WeatherInteractorImpl(repository)

    private val repository: WeatherRepository
        get() = WeatherRepositoryImpl(networkDataSource)

    fun getViewModelFactory(city: City): WeatherViewModelFactory {
        return WeatherViewModelFactory(interactor, city)
    }

}