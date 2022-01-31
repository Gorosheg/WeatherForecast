package com.first.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.first.common.CityNavigator
import com.first.common.WeatherNavigator
import com.first.common.model.City
import com.first.common.util.CITY_KEY
import com.first.weatherScreen.presentation.WeatherFragment

class NavigatorImpl : CityNavigator, WeatherNavigator, MainNavigator {

//    override var fragmentCount = 0

    override fun navigateToWeatherScreen(activity: FragmentActivity, city: City) {
        val act: MainActivity = activity as MainActivity
        val bundle = Bundle()
        bundle.putSerializable(CITY_KEY, city)
        val fragment = WeatherFragment()
        fragment.arguments = bundle
        act.createWeatherFragment(fragment)
    }

    // todo в активити трекать онбекпрессед. возможно тогда отсюда убрать ремув и вызывать онбекпрес активити
    override fun closeScreen(fragment: Fragment, activity: FragmentActivity) {
        activity.supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
    }

    override fun openCitiesScreen(activity: FragmentActivity, fragment: Fragment) {
        activity.supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentHolder, fragment)
            .addToBackStack("CitiesScreen")
            .commit()
    }

    override fun closeWeatherScreen(activity: FragmentActivity) {
        activity.supportFragmentManager.popBackStack()
    }
}
