package com.first.network

import com.first.network.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather?")
    fun getWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String = "7c222c6d18458260fd5451268fee4ed9"
    ): Single<WeatherResponse>

    @GET("/data/2.5/weather?")
    fun getWeatherByName(
        @Query("q") cityName: String,
        @Query("appid") appId: String = "7c222c6d18458260fd5451268fee4ed9"
    ): Single<WeatherResponse>

}