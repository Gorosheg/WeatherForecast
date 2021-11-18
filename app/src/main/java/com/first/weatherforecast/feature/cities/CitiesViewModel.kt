package com.first.weatherforecast.feature.cities

import androidx.lifecycle.ViewModel
import com.first.weatherforecast.datasource.database.CitiesDatabaseDatasource
import com.first.weatherforecast.datasource.network.loadingWeather
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.model.City
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class CitiesViewModel : ViewModel() {

    private val db by lazy { CitiesDatabaseDatasource() }

    private val _error: Subject<Throwable> = PublishSubject.create()
    val error: Observable<Throwable> = _error

    fun loadData(): Observable<List<City>> {
        return db.getAllCities()
    }

    fun loadWeather(city: City) {
        loadingWeather(city = city)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                val newCity = copyCity(city, it)
                addCity(newCity)
            }
            .doOnError(_error::onNext)
            .subscribe()
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

    fun removeCity(city: City) {
        db.deleteCity(city)
    }
}