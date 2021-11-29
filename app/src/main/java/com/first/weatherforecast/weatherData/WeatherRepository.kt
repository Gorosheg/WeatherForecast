package com.first.weatherforecast.weatherData

import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Single

interface WeatherRepository {
    fun loadWeather(city: City): Single<WeatherResponse>
}