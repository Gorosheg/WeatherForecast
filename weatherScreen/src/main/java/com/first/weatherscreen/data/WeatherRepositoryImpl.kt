package com.first.weatherscreen.data

import com.first.common.model.City
import com.first.network.NetworkDataSource
import com.first.network.model.WeatherResponse
import io.reactivex.Single

class WeatherRepositoryImpl(private val networkDataSource: NetworkDataSource) : WeatherRepository {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return networkDataSource.loadingWeather(city)
    }

}