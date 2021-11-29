package com.first.weatherforecast.weatherPresentation

import androidx.lifecycle.ViewModel
import com.first.weatherforecast.App
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import com.first.weatherforecast.weatherDomain.WeatherInteractor
import io.reactivex.Single

class WeatherViewModel : ViewModel() {

    private val interactor: WeatherInteractor by lazy { App.weatherDi.interactor }

    fun loadWeather(city: City): Single<WeatherResponse> {
        return interactor.loadWeather(city = city)
    }
}