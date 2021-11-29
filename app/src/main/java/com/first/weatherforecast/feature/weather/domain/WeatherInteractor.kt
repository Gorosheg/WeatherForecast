package com.first.weatherforecast.feature.weather.domain

import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import io.reactivex.Single

interface WeatherInteractor {
    fun loadWeather(city: City): Single<WeatherResponse>
}