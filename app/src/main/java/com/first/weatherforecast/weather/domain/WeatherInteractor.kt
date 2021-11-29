package com.first.weatherforecast.weather.domain

import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Single

interface WeatherInteractor {
    fun loadWeather(city: City): Single<WeatherResponse>
}