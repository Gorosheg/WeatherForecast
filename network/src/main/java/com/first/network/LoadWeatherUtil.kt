package com.first.network

import com.first.common.model.City
import com.first.network.model.WeatherResponse
import io.reactivex.Single

class NetworkDataSource(private val api: WeatherApi) {

    fun loadingWeather(city: City): Single<WeatherResponse> {
        val coordinates = city.coordinates

        return if (coordinates != null) {
            api.getWeatherByCoordinates(
                latitude = coordinates.latitude,
                longitude = coordinates.longitude
            )
        } else {
            val name = city.name ?: throw IllegalStateException("Can't find a name")
            api.getWeatherByName(cityName = name)
        }
    }
}
