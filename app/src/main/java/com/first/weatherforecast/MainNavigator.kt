package com.first.weatherforecast

import androidx.fragment.app.FragmentActivity

interface MainNavigator {

    fun navigateToCitiesScreen(activity: FragmentActivity)

    fun back(activity: FragmentActivity)

}