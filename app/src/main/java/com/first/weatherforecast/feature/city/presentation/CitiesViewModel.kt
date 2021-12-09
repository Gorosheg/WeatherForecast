package com.first.weatherforecast.feature.city.presentation

import androidx.lifecycle.ViewModel
import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.datasource.network.model.WeatherResponse
import com.first.weatherforecast.feature.city.domain.CitiesInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class CitiesViewModel(private val interactor: CitiesInteractor) : ViewModel() {

    // private val interactor: CitiesInteractor by lazy { App.citiesDi(City).interactor }

    private val _error: Subject<Throwable> = PublishSubject.create()
    val error: Observable<Throwable> = _error

    val cities: Observable<List<City>>
        get() = interactor.cities

    fun loadWeather(city: City) {
        interactor.loadWeather(city = city)
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
        interactor.addCity(city)
    }

    fun removeCity(city: City) {
        interactor.removeCity(city)
    }

    fun isFirstLaunch(): Boolean? {
        return interactor.isFirstLaunch()
    }
}