package com.first.weatherScreen.data

import com.first.common.model.City
import com.first.network.NetworkDataSource
import com.first.network.model.WeatherResponse
import io.reactivex.Single

internal class WeatherRepositoryImpl(private val networkDataSource: NetworkDataSource) : WeatherRepository {

    override fun loadWeather(city: City): Single<WeatherResponse> {
        return networkDataSource.loadingWeather(city)
    }

}