package com.first.weatherscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.first.common.model.City
import com.first.weatherscreen.domain.WeatherInteractor

class WeatherViewModelFactory(
    private val interactor: WeatherInteractor,
    private val city: City
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(interactor, city) as T
    }
}