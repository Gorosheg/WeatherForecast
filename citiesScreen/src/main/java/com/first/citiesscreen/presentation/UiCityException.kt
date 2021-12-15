package com.first.citiesscreen.presentation

sealed class UiCityExceptions {

    object NotFound : UiCityExceptions()
    object Unknown : UiCityExceptions()

}