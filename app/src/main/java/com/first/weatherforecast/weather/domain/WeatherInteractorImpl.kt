package com.first.weatherforecast.weather.domain

import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import com.first.weatherforecast.weather.data.WeatherRepository
import io.reactivex.Single

class WeatherInteractorImpl(private val repository: WeatherRepository) : WeatherInteractor {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return repository.loadWeather(city)
    }

}