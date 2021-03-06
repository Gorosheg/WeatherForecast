package com.first.common.model

data class Weather(
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val degree: Int,
    val tempMax: Int,
    val tempMin: Int,
    val feelsLike: Int,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val skyCondition: SkyCondition,
    val skyImage: SkyImage,
    val currentDate: String,
    val currentDay: String
)