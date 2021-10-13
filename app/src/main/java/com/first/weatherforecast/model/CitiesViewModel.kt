package com.first.weatherforecast.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.first.weatherforecast.database.CitiesDatabaseDatasource
import com.first.weatherforecast.network.loadWeather
import com.first.weatherforecast.ui.model.City

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
                city.name = it.city
                addCity(city)
                loadData()
            },
            onFailure = {
                _toast.value = it
            }
        )

    }

    private fun addCity(city: City) {
        db.addCity(city)
    }


}