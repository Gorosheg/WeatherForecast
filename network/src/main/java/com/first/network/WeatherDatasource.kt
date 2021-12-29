package com.first.network

import com.first.common.model.City
import com.first.common.model.Coordinates
import com.first.common.model.Weather
import com.first.network.model.WeatherResponse
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

interface WeatherDatasource {
    fun loadingWeather(city: City): Single<Weather>
}

internal class NetworkDataSource(private val api: WeatherApi) : WeatherDatasource {

    override fun loadingWeather(city: City): Single<Weather> {
        val coordinates = city.coordinates

        return if (coordinates != null) {
            getWeather(coordinates)
        } else {
            getWeather(city)
        }
    }

    private fun getWeather(coordinates: Coordinates): Single<Weather> {
        return api.getWeather(
            latitude = coordinates.latitude,
            longitude = coordinates.longitude
        ).map { it.toWeather() }
    }

    private fun getWeather(city: City): Single<Weather> {
        val name = city.name ?: throw IllegalStateException("Can't find a name")

        return api.getWeather(name)
            .map { it.toWeather() }
    }

    private fun WeatherResponse.toWeather(): Weather {
        val currentDate: String = SimpleDateFormat("MMM dd", Locale.ENGLISH).format(Date())
        val currentDay: String = SimpleDateFormat("EEEE", Locale.ENGLISH).format(Date())
        return Weather(
            cityName = cityName,
            latitude = latitude,
            longitude = longitude,
            degree = degree,
            tempMax = tempMax,
            tempMin = tempMin,
            feelsLike = feelsLike,
            humidity = humidity,
            pressure = pressure,
            windSpeed = windSpeed,
            skyCondition = skyCondition,
            skyImage = skyImage,
            currentDate = currentDate,
            currentDay = currentDay
        )
    }
}
