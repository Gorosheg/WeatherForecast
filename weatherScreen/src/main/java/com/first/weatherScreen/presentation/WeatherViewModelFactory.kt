package com.first.weatherScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.first.common.model.City
import com.first.weatherScreen.domain.WeatherInteractor

internal class WeatherViewModelFactory(
    private val interactor: WeatherInteractor,
    private val city: City
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(interactor, city) as T
    }
}