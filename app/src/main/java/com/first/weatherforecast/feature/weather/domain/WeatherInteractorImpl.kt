package com.first.weatherforecast.feature.weather.domain

import com.first.weatherforecast.common.model.City
import com.first.network.model.WeatherResponse
import com.first.weatherforecast.feature.weather.data.WeatherRepository
import io.reactivex.Single

class WeatherInteractorImpl(private val repository: WeatherRepository) : WeatherInteractor {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return repository.loadWeather(city)
    }

}