package com.first.weatherforecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.first.citiesscreen.presentation.CitiesFragment

class MainActivity : AppCompatActivity() {

    private val navigatorDi by lazy { NavigatorDi.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val citiesFragment = CitiesFragment()
        createCitiesFragment(citiesFragment)
    }

    private fun createCitiesFragment(fragment: Fragment) {
        navigatorDi.mainNavigator.openCitiesScreen(this, fragment)
    }

    fun createWeatherFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentHolder, fragment)
            commit()
        }
    }

}