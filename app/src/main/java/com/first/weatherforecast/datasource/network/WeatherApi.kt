package com.first.weatherforecast.datasource.network

import com.first.weatherforecast.datasource.network.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather?")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String = "7c222c6d18458260fd5451268fee4ed9"
    ): Call<WeatherResponse>

}