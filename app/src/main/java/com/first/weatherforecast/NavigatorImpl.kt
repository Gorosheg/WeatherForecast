package com.first.weatherforecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.first.citiesscreen.presentation.CitiesFragment
import com.first.common.CityNavigator
import com.first.common.WeatherNavigator
import com.first.common.model.City
import com.first.weatherScreen.presentation.WeatherFragment

private const val WEATHER_SCREEN = "WeatherScreen"
private const val CITIES_SCREEN = "CitiesScreen"

class NavigatorImpl : CityNavigator, WeatherNavigator, MainNavigator {

    override fun navigateToWeatherScreen(activity: FragmentActivity, city: City) {
        val fragment = WeatherFragment.newInstance(city)
        activity.navigateToWeatherFragment(fragment)
    }

    private fun FragmentActivity.navigateToWeatherFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentHolder, fragment)
            addToBackStack(WEATHER_SCREEN)
            commit()
        }
    }

    override fun navigateToCitiesScreen(activity: FragmentActivity) {
        val citiesFragment = CitiesFragment.newInstance()

        activity.supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentHolder, citiesFragment)
            .addToBackStack(CITIES_SCREEN)
            .commit()
    }

    override fun back(activity: FragmentActivity) {
        activity.supportFragmentManager.popBackStack()
    }
}
