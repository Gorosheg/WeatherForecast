package com.first.feature.citiesscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.first.feature.citiesscreen.domain.CitiesInteractor

class CitiesViewModelFactory(private val interactor: CitiesInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CitiesViewModel(interactor) as T
    }

}