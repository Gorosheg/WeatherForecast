package com.first.weatherforecast.weather.data

import com.first.weatherforecast.datasource.database.NetworkDataSource
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Single

class WeatherRepositoryImpl(private val networkDataSource: NetworkDataSource) : WeatherRepository {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return networkDataSource.loadingWeather(city)
    }

}