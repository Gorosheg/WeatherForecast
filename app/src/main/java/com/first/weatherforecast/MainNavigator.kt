package com.first.weatherforecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface MainNavigator {

    fun openCitiesScreen(activity: FragmentActivity, fragment: Fragment)

}