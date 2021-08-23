package com.first.weatherforecast.network

import com.first.weatherforecast.model.Weather
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {

    @GET("/data/2.5/weather?lat=59.9386&lon=30.3141&appid=7c222c6d18458260fd5451268fee4ed9")
    fun getWeather(): Call<Weather>

}