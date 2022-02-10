package com.first.feature.citiesscreen.presentation

internal sealed class UiCityExceptions {

    object NotFound : UiCityExceptions()
    object Unknown : UiCityExceptions()

}