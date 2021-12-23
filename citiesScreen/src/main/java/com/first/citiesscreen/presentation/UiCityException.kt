package com.first.citiesscreen.presentation

internal sealed class UiCityExceptions {

    object NotFound : UiCityExceptions()
    object Unknown : UiCityExceptions()

}