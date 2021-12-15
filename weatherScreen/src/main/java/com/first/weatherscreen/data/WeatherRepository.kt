package com.first.weatherscreen.data

import com.first.common.model.City
import com.first.network.model.WeatherResponse
import io.reactivex.Single

interface WeatherRepository {
    fun loadWeather(city: City): Single<WeatherResponse>
}