package com.first.weatherforecast.feature.city.presentation

sealed class UiCityExceptions {

    object NotFound : UiCityExceptions()
    object Unknown : UiCityExceptions()

}