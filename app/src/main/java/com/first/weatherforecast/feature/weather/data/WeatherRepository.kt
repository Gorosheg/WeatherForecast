package com.first.weatherforecast.feature.weather.data

import com.first.weatherforecast.common.model.City
import com.first.network.model.WeatherResponse
import io.reactivex.Single

interface WeatherRepository {
    fun loadWeather(city: City): Single<WeatherResponse>
}