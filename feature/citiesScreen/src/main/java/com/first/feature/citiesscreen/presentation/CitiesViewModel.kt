package com.first.feature.citiesscreen.presentation

import androidx.lifecycle.ViewModel
import com.first.feature.citiesscreen.domain.CitiesInteractor
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

    private var _isNoItems = PublishSubject.create<Boolean>()
    val isNoItems: Observable<Boolean> = _isNoItems

    val cities: Observable<List<City>>
        get() = interactor.cities

    val isFirstLaunch: Boolean
        get() = interactor.isFirstLaunch

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun loadWeather(city: City) {
        disposable += interactor.loadWeather(city = city)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(::handleError)
            .ignoreElement()
            .onErrorComplete()
            .subscribe()
    }

    private fun handleError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> UiCityExceptions.NotFound
            else -> UiCityExceptions.Unknown
        }

        _error.onNext(UiCityExceptions.NotFound)
    }

    fun removeCity(city: City) {
        interactor.removeCity(city)
        checkIsEmpty()
    }

    fun changeFavoriteState(city: City) {
        interactor.changeFavoriteState(city)
    }

    fun checkIsEmpty() {
        _isNoItems.onNext(interactor.isNoItems)
    }
}