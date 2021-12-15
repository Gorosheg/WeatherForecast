package com.first.citiesscreen.presentation

import androidx.lifecycle.ViewModel
import com.first.citiesscreen.domain.CitiesInteractor
import com.first.network.model.WeatherResponse
import com.first.common.model.City
import com.first.common.model.Coordinates
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException

class CitiesViewModel(private val interactor: CitiesInteractor) : ViewModel() {
    private var disposable = CompositeDisposable()
    private val _error = PublishSubject.create<UiCityExceptions>()
    val error: Observable<UiCityExceptions> = _error

    val cities: Observable<List<City>>
        get() = interactor.cities

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun loadWeather(city: City) {
        disposable += interactor.loadWeather(city = city)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                val newCity = copyCity(city, it)
                if (!isCityExist(newCity)) {
                    addCity(newCity)
                }
            }
            .doOnError {
                when (it) {
                    is HttpException -> UiCityExceptions.NotFound
                    else -> UiCityExceptions.Unknown
                }
                _error.onNext(UiCityExceptions.NotFound)
            }
            .ignoreElement()
            .onErrorComplete()
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
                coordinates = Coordinates(response.latitude, response.longitude)
            )
        }
    }

    private fun addCity(city: City) {
        interactor.addCity(city)
    }

    private fun isCityExist(city: City): Boolean {
        return interactor.isCityExist(city)
    }

    fun removeCity(city: City) {
        interactor.removeCity(city)
    }

    fun isFirstLaunch(): Boolean? {
        return interactor.isFirstLaunch()
    }
}