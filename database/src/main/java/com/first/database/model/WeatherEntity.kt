package com.first.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.first.common.model.SkyCondition
import com.first.common.model.SkyImage
import com.first.common.model.Weather


@Entity
internal data class WeatherEntity(
    val latitude: Double,
    val longitude: Double,
    val degree: Int,
    val tempMax: Int,
    val tempMin: Int,
    val feelsLike: Int,
    val humidity: Int,
    val pressure: Int,
    val skyCondition: SkyCondition,
    val skyImage: SkyImage,
    @PrimaryKey
    val cityName: String
)

internal fun Weather.toEntity() = WeatherEntity(
    latitude = latitude,
    longitude = longitude,
    degree = degree,
    tempMax = tempMax,
    tempMin = tempMin,
    feelsLike = feelsLike,
    humidity = humidity,
    pressure = pressure,
    skyCondition = skyCondition,
    skyImage = skyImage,
    cityName = cityName
)

// С ссылкой на функцию - map(WeatherEntity::toSimpleWeather)
internal fun List<WeatherEntity>.toSimpleWeather(): List<Weather> = map { weather ->
    weather.toSimpleWeather()
}

internal fun WeatherEntity.toSimpleWeather() = Weather(
    latitude = latitude,
    longitude = longitude,
    degree = degree,
    tempMax = tempMax,
    tempMin = tempMin,
    feelsLike = feelsLike,
    humidity = humidity,
    pressure = pressure,
    skyCondition = skyCondition,
    skyImage = skyImage,
    cityName = cityName
)