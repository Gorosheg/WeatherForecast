package com.first.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface WeatherNavigator {
    fun closeScreen(fragment: Fragment, activity: FragmentActivity)
}