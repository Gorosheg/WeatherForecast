package com.first.weatherforecast

import android.annotation.SuppressLint
import android.app.Application
import com.first.weatherforecast.datasource.sharedPreference.MutablePreferenceDatasource
import com.first.weatherforecast.datasource.sharedPreference.SharedPrefValues
import com.first.weatherforecast.datasource.sharedPreference.SharedPreferenceDi
import com.first.weatherforecast.datasource.database.DatabaseDi
import com.first.weatherforecast.datasource.network.NetworkDi
import com.first.weatherforecast.feature.city.dI.CitiesDi
import com.first.weatherforecast.feature.weather.dI.WeatherDi

@SuppressLint("StaticFieldLeak")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        databaseDi = DatabaseDi(this)
        sharedPrefDi = SharedPreferenceDi(this)

        initIsFirstLaunchPref(sharedPrefDi.mutablePreferenceDatasource)
    }

    private fun initIsFirstLaunchPref(preferences: MutablePreferenceDatasource) {
        when (preferences.isFirstLaunchEnum) {
            SharedPrefValues.DEFAULT -> {
                preferences.isFirstLaunchEnum = SharedPrefValues.TRUE
            }
            SharedPrefValues.TRUE -> {
                preferences.isFirstLaunchEnum = SharedPrefValues.FALSE
            }
        }
    }

    companion object {

        private lateinit var sharedPrefDi: SharedPreferenceDi
        private lateinit var databaseDi: DatabaseDi
        private val networkDi by lazy { NetworkDi() }

        val citiesDi by lazy {
            CitiesDi(
                databaseDi.datasource,
                networkDi.networkDataSource,
                sharedPrefDi.preferenceDatasource
            )
        }
        val weatherDi by lazy { WeatherDi(networkDi.networkDataSource) }

    }

}