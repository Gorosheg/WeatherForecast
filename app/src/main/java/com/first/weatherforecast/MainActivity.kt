package com.first.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.first.citiesscreen.presentation.CitiesFragment
//1 прокидывать только fragmentHolder
//2 прокидывать не через активити а через интерейс
//3 createCitiesFragment тоже унести в NavigatorImpl через новый интерфейс MainNavigator
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val citiesFragment = CitiesFragment()
        createCitiesFragment(citiesFragment)
    }

    private fun createCitiesFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentHolder, fragment)
            commit()
        }
    }

    fun createWeatherFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentHolder, fragment)
            commit()
        }
    }

}