package com.first.weatherforecast.feature.weather.presentation

import androidx.lifecycle.ViewModel
import com.first.weatherforecast.common.model.City
import com.first.network.model.WeatherResponse
import com.first.weatherforecast.feature.weather.domain.WeatherInteractor
import io.reactivex.Single

class WeatherViewModel(
    private val interactor: WeatherInteractor,
    private val city: City
) : ViewModel() {

    fun loadWeather(): Single<WeatherResponse> {
        return interactor.loadWeather(city)
    }
}