package com.first.weatherforecast.network

import com.first.weatherforecast.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather?")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String = "7c222c6d18458260fd5451268fee4ed9"
    ): Call<Weather>

}
//59.939099, 30.315877
//59.9389763 30.3166046