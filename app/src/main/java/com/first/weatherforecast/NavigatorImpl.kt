package com.first.weatherforecast

import androidx.fragment.app.FragmentActivity
import com.first.common.CityNavigator
import com.first.common.WeatherNavigator
import com.first.common.model.City
import com.first.weatherScreen.presentation.WeatherFragment

class NavigatorImpl : CityNavigator, WeatherNavigator {

    override fun navigateToWeatherScreen(activity: FragmentActivity, city: City) {
        val act: MainActivity = activity as MainActivity
        val fragment = WeatherFragment(city) // todo через интент с бандлом
        act.createWeatherFragment(fragment)
    }

    override fun closeScreen(activity: FragmentActivity?) {
        activity?.supportFragmentManager?.popBackStack()
    }

}