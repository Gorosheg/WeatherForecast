package com.first.feature.weatherScreen.presentation

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

fun Activity.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}