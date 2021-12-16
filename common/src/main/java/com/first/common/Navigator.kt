package com.first.common

import android.app.Activity
import com.first.common.model.City

const val CITY_KEY = "CITY_KEY"

interface Navigator {
    fun navigateToWeatherScreen(activity: Activity, city: City)
}