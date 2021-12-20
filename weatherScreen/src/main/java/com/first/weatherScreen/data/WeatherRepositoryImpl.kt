package com.first.weatherScreen.data

import com.first.common.model.City
import com.first.common.model.Weather
import com.first.network.WeatherDatasource
import io.reactivex.Single

internal class WeatherRepositoryImpl(private val networkDataSource: WeatherDatasource) : WeatherRepository {

    override fun loadWeather(city: City): Single<Weather> {
        return networkDataSource.loadingWeather(city)
    }

}