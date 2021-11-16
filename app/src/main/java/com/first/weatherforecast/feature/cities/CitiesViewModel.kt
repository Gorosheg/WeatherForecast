package com.first.weatherforecast.feature.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource
import com.first.weatherforecast.util.SingleLifeEvent
import com.first.weatherforecast.datasource.network.loadWeather
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Observable

class CitiesViewModel : ViewModel() {

    private val db by lazy { CitiesDatabaseDatasource() }

    private var _error: MutableLiveData<Throwable> = SingleLifeEvent<Throwable>()
    val error: LiveData<Throwable> = _error

    fun loadData(): Observable<List<City>> {
        return db.getAllCities()
    }

    fun loadWeather(city: City) {
        loadWeather(
            city = city,
            onSuccess = {
                val newCity = copyCity(city, it)
                addCity(newCity)
            },
            onFailure = {
                _error.value = it
            }
        )
    }

    fun removeCity(city: City) {
        db.deleteCity(city)
    }

    private fun copyCity(
        city: City,
        response: WeatherResponse
    ): City {
        return if (city.name == null) {
            city.copy(
                name = response.city
            )
        } else {
            city.copy(
                latitude = response.latitude,
                longitude = response.longitude
            )
        }
    }

    private fun addCity(city: City) {
        db.addCity(city)
    }
}