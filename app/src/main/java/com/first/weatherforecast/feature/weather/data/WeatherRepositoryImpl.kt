package com.first.weatherforecast.feature.weather.data

import com.first.database.NetworkDataSource
import com.first.weatherforecast.common.model.City
import com.first.network.model.WeatherResponse
import io.reactivex.Single

class WeatherRepositoryImpl(private val networkDataSource: NetworkDataSource) : WeatherRepository {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return networkDataSource.loadingWeather(city)
    }

}