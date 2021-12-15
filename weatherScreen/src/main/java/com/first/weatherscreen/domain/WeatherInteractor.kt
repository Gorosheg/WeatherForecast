package com.first.weatherscreen.domain

import com.first.common.model.City
import com.first.network.model.WeatherResponse
import io.reactivex.Single

interface WeatherInteractor {
    fun loadWeather(city: City): Single<WeatherResponse>
}