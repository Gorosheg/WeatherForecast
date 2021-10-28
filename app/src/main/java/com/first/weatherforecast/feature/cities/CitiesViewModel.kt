package com.first.weatherforecast.feature.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource
import com.first.weatherforecast.util.SingleLifeEvent
import com.first.weatherforecast.datasource.network.loadWeather
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City

class CitiesViewModel : ViewModel() {

    private val db by lazy { CitiesDatabaseDatasource() }

    private var _data = MutableLiveData<List<City>>()
    private var _toast: MutableLiveData<Throwable> = SingleLifeEvent<Throwable>()
    val data: LiveData<List<City>> = _data
    val toast: LiveData<Throwable> = _toast

    fun loadData() {
        _data.value = db.getAllCities()
    }

    fun loadWeather(city: City) {
        loadWeather(
            city = city,
            onSuccess = {
                val newCity = copyCity(city, it)
                addCity(newCity)
                loadData()
            },
            onFailure = {
                _toast.value = it
            }
        )
    }

    private fun copyCity(
        city: City,
        it: WeatherResponse
    ): City {
        if (city.name == null) {
            val newCity = city.copy(
                name = it.city
            )
            return newCity
        } else {
            val newCity = city.copy(
                latitude = it.latitude,
                longitude = it.longitude
            )
            return newCity
        }
    }

    private fun addCity(city: City) {
        db.addCity(city)
    }
}