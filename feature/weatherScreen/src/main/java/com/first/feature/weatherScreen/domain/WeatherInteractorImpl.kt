package com.first.feature.weatherScreen.domain

import com.first.common.model.City
import com.first.common.model.Weather
import com.first.feature.weatherScreen.data.WeatherRepository
import io.reactivex.Single

internal class WeatherInteractorImpl(private val repository: WeatherRepository) :
    WeatherInteractor {

    override fun loadWeather(city: City): Single<Weather> {
        return repository.loadWeather(city)
    }

    override fun getSavingData(city: City): Weather? {
        return repository.getSavingCity(city)
    }

}