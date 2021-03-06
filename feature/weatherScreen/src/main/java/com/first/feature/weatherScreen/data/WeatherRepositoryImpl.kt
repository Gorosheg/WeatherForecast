package com.first.feature.weatherScreen.data

import com.first.common.model.City
import com.first.common.model.Weather
import com.first.dataSource.database.CitiesDatabaseDatasource
import com.first.dataSource.network.WeatherDatasource
import io.reactivex.Single

internal class WeatherRepositoryImpl(
    private val networkDataSource: WeatherDatasource,
    private val database: CitiesDatabaseDatasource,
) : WeatherRepository {

    override fun loadWeather(city: City): Single<Weather> {
        return networkDataSource.loadingWeather(city).doOnSuccess { saveWeather(it) }
    }

    private fun saveWeather(weather: Weather) {
        database.update(weather)
    }

    override fun getSavingCity(city: City): Weather? {
        return database.getCity(city)
    }

}