package com.first.weatherscreen.dI

import com.first.common.model.City
import com.first.network.NetworkDataSource
import com.first.weatherscreen.data.WeatherRepository
import com.first.weatherscreen.data.WeatherRepositoryImpl
import com.first.weatherscreen.domain.WeatherInteractor
import com.first.weatherscreen.domain.WeatherInteractorImpl
import com.first.weatherscreen.presentation.WeatherViewModelFactory

class WeatherDi(private val networkDataSource: NetworkDataSource) {

    private val interactor: WeatherInteractor
        get() = WeatherInteractorImpl(repository)

    private val repository: WeatherRepository
        get() = WeatherRepositoryImpl(networkDataSource)

    fun getViewModelFactory(city: City): WeatherViewModelFactory {
        return WeatherViewModelFactory(interactor, city)
    }

    companion object {

        lateinit var weatherDi: WeatherDi

    }

}