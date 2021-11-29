package com.first.weatherforecast.datasource.database

import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.datasource.network.WeatherApi
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import io.reactivex.Single

class NetworkDataSource(private val api: WeatherApi) {

    fun loadingWeather(city: City): Single<WeatherResponse> {
        return if (city.latitude != null && city.longitude != null) {
            api.getWeatherByCoordinates(latitude = city.latitude, longitude = city.longitude)
        } else {
            val name = city.name ?: throw IllegalStateException("Can't find a name")
            api.getWeatherByName(cityName = name)
        }
    }
}
