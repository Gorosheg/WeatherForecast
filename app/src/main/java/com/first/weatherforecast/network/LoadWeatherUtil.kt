package com.first.weatherforecast.network

import com.first.weatherforecast.model.City
import com.first.weatherforecast.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @param onSuccess - В случае успешного получения данных передает Weather тому,
 * кто вызвал функцию для дальнейших действий.
 * @param onFailure - В случае неудачи передает Throwable тому, кто вызвал функцию
 *
 * Статическая функция, которая не делает ничего лишнего, не вызывает никаких сайд эффектов
 * У функции есть только входые и выходные данные
 */
fun loadWeather(
    city: City,
    onSuccess: (Weather) -> Unit,
    onFailure: (Throwable) -> Unit
) {
    val request = object : Callback<Weather> {

        override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
            val weather: Weather = response.body() ?: return
            onSuccess.invoke(weather)
        }

        override fun onFailure(call: Call<Weather>, t: Throwable) {
            onFailure.invoke(Throwable())
        }
    }

    NetworkManager.api()
        ?.getWeather(latitude = city.latitude, longitude = city.longitude)
        ?.enqueue(request)
}