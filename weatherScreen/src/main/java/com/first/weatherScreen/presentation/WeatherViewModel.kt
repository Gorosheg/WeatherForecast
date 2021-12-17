package com.first.weatherScreen.presentation

import androidx.lifecycle.ViewModel
import com.first.common.model.City
import com.first.network.model.WeatherResponse
import com.first.weatherScreen.domain.WeatherInteractor
import io.reactivex.Single

internal class WeatherViewModel(
    private val interactor: WeatherInteractor,
    private val city: City
) : ViewModel() {

    fun loadWeather(): Single<WeatherResponse> {
        return interactor.loadWeather(city)
    }
}