package com.first.weatherScreen.presentation

import androidx.lifecycle.ViewModel
import com.first.common.model.City
import com.first.common.model.Weather
import com.first.weatherScreen.domain.WeatherInteractor
import io.reactivex.Single

internal class WeatherViewModel(
    private val interactor: WeatherInteractor,
    private val city: City
) : ViewModel() {

    fun loadWeather(): Single<Weather> {
        return interactor.loadWeather(city)
    }

    fun getSavingData(): Weather? {
        return interactor.getSavingData(city)
    }
}