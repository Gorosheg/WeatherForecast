package com.first.weatherscreen.presentation

import androidx.lifecycle.ViewModel
import com.first.common.model.City
import com.first.network.model.WeatherResponse
import com.first.weatherscreen.domain.WeatherInteractor
import io.reactivex.Single

class WeatherViewModel(
    private val interactor: WeatherInteractor,
    private val city: City
) : ViewModel() {

    fun loadWeather(): Single<WeatherResponse> {
        return interactor.loadWeather(city)
    }
}