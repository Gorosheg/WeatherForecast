package com.first.weatherforecast

import android.app.Activity
import android.content.Intent
import com.first.common.CITY_KEY
import com.first.common.Navigator
import com.first.common.model.City
import com.first.weatherscreen.presentation.WeatherActivity

class NavigatorImpl : Navigator {

    override fun navigateToWeatherScreen(activity: Activity, city: City) {
        val intent = Intent(activity, WeatherActivity::class.java)
        intent.putExtra(CITY_KEY, city)
        activity.startActivity(intent)
    }

}