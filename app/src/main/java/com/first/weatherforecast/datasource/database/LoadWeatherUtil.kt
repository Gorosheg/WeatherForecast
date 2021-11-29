package com.first.weatherforecast.datasource.database

import com.first.weatherforecast.datasource.network.NetworkManager
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Single
class NetworkDataSource{

    fun loadingWeather(city: City): Single<WeatherResponse> {
        return if (city.latitude != null && city.longitude != null) {
            NetworkManager.api()
                .getWeatherByCoordinates(latitude = city.latitude, longitude = city.longitude)
        } else {
            val name = city.name ?: throw IllegalStateException("Can't find a name")
            NetworkManager.api()
                .getWeatherByName(cityName = name)
        }
    }
}
