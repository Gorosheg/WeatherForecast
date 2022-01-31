package com.first.weatherforecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val navigatorDi by lazy { NavigatorDi.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createCitiesFragment()
    }

    override fun onBackPressed() {
        val fragmentsCount = supportFragmentManager.fragments.size

        if (fragmentsCount == 2) {
            navigatorDi.mainNavigator.back(this)
        } else {
            super.onBackPressed()
        }
    }

    private fun createCitiesFragment() {
        navigatorDi.mainNavigator.navigateToCitiesScreen(this)
    }

}