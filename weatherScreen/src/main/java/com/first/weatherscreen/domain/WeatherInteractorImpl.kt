package com.first.weatherscreen.domain

import com.first.common.model.City
import com.first.network.model.WeatherResponse
import com.first.weatherscreen.data.WeatherRepository
import io.reactivex.Single

class WeatherInteractorImpl(private val repository: WeatherRepository) : WeatherInteractor {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return repository.loadWeather(city)
    }

}