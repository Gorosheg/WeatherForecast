package com.first.weatherforecast.feature.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.feature.weather.domain.WeatherInteractor

class WeatherViewModelFactory(
    private val interactor: WeatherInteractor,
    private val city: City
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(interactor, city) as T
    }
}