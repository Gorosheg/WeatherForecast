package com.first.citiesscreen.presentation

import androidx.lifecycle.ViewModel
import com.first.citiesscreen.domain.CitiesInteractor
import com.first.common.model.City
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException

internal class CitiesViewModel(private val interactor: CitiesInteractor) : ViewModel() {
    private var disposable = CompositeDisposable()
    private val _error = PublishSubject.create<UiCityExceptions>()
    val error: Observable<UiCityExceptions> = _error

    private var _isEmpty = PublishSubject.create<Boolean>()
    val isEmpty: Observable<Boolean> = _isEmpty

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

    fun removeCity(city: City) {
        interactor.removeCity(city)
        isEmpty()
    }

    fun isFirstLaunch(): Boolean? {
        return interactor.isFirstLaunch()
    }

    fun isEmpty() {
        _isEmpty.onNext(interactor.isEmpty())
    }
}