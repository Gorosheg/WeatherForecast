package com.first.weatherforecast.weatherData

import com.first.weatherforecast.datasource.database.NetworkDataSource
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Single

class WeatherRepositoryImpl() : WeatherRepository {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return NetworkDataSource().loadingWeather(city)
    }

}