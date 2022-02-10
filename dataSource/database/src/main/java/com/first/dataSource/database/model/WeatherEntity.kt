package com.first.dataSource.database.model

import com.first.common.model.SkyCondition
import com.first.common.model.SkyImage
import com.first.common.model.Weather

internal data class WeatherEntity(
    val cityLatitude: Double,
    val cityLongitude: Double,
    val degree: Int,
    val tempMax: Int,
    val tempMin: Int,
    val feelsLike: Int,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val skyCondition: SkyCondition,
    val skyImage: SkyImage,
    val cityName: String,
    val currentDate: String,
    val currentDay: String
)

internal fun Weather.toEntity() = WeatherEntity(
    cityLatitude = latitude,
    cityLongitude = longitude,
    degree = degree,
    tempMax = tempMax,
    tempMin = tempMin,
    feelsLike = feelsLike,
    humidity = humidity,
    pressure = pressure,
    windSpeed = windSpeed,
    skyCondition = skyCondition,
    skyImage = skyImage,
    cityName = cityName,
    currentDate = currentDate,
    currentDay = currentDay
)

// С ссылкой на функцию - map(WeatherEntity::toSimpleWeather)
internal fun List<WeatherEntity>.toSimpleWeather(): List<Weather> = map { weather ->
    weather.toSimpleWeather()
}

internal fun WeatherEntity.toSimpleWeather() = Weather(
    latitude = cityLatitude,
    longitude = cityLongitude,
    degree = degree,
    tempMax = tempMax,
    tempMin = tempMin,
    feelsLike = feelsLike,
    humidity = humidity,
    pressure = pressure,
    windSpeed = windSpeed,
    skyCondition = skyCondition,
    skyImage = skyImage,
    cityName = cityName,
    currentDate = currentDate,
    currentDay = currentDay
)