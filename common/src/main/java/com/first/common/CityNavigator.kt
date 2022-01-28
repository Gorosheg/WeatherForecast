package com.first.common

import androidx.fragment.app.FragmentActivity
import com.first.common.model.City

interface CityNavigator {
    fun navigateToWeatherScreen(activity: FragmentActivity, city: City)
}