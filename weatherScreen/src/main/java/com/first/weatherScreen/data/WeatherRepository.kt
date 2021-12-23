package com.first.weatherScreen.data

import com.first.common.model.City
import com.first.common.model.Weather
import io.reactivex.Single

internal interface WeatherRepository {
    fun loadWeather(city: City): Single<Weather>

    fun getSavingCity(city: City): Weather?
}