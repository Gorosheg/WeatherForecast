package com.first.weatherforecast.weatherDI

import com.first.weatherforecast.weatherData.WeatherRepository
import com.first.weatherforecast.weatherData.WeatherRepositoryImpl
import com.first.weatherforecast.weatherDomain.WeatherInteractor
import com.first.weatherforecast.weatherDomain.WeatherInteractorImpl

class WeatherDI () {

    val interactor: WeatherInteractor
        get() = WeatherInteractorImpl(repository)

    private val repository: WeatherRepository
        get() = WeatherRepositoryImpl()

}