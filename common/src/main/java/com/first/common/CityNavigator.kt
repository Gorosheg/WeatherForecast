package com.first.common

import androidx.fragment.app.FragmentActivity
import com.first.common.model.City

const val CITY_KEY = "CITY_KEY"

interface CityNavigator {
    fun navigateToWeatherScreen(activity: FragmentActivity, city: City)
}