package com.first.weatherScreen.domain

import com.first.common.model.City
import com.first.network.model.WeatherResponse
import com.first.weatherScreen.data.WeatherRepository
import io.reactivex.Single

internal class WeatherInteractorImpl(private val repository: WeatherRepository) : WeatherInteractor {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return repository.loadWeather(city)
    }

}