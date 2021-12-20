package com.first.weatherScreen.domain

import com.first.common.model.City
import com.first.common.model.Weather
import io.reactivex.Single

interface WeatherInteractor {
    fun loadWeather(city: City): Single<Weather>
}