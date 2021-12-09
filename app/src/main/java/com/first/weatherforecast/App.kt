package com.first.weatherforecast

import android.annotation.SuppressLint
import android.app.Application
import com.first.weatherforecast.datasource.database.DatabaseDi
import com.first.weatherforecast.datasource.network.NetworkDi
import com.first.weatherforecast.datasource.sharedPreference.FirstLaaunchEnum
import com.first.weatherforecast.datasource.sharedPreference.MutablePreferenceDatasource
import com.first.weatherforecast.datasource.sharedPreference.SharedPreferenceDi
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
        if (preferences.firstLaaunchEnum == FirstLaaunchEnum.DEFAULT) {
            preferences.firstLaaunchEnum = FirstLaaunchEnum.TRUE
        } else if (preferences.firstLaaunchEnum == FirstLaaunchEnum.TRUE) {
            preferences.firstLaaunchEnum = FirstLaaunchEnum.FALSE
        }
    }

    companion object {

        private lateinit var sharedPrefDi: SharedPreferenceDi
        private lateinit var databaseDi: DatabaseDi
        private val networkDi by lazy { NetworkDi() }

        val citiesDi by lazy {
            CitiesDi(
                datasource = databaseDi.datasource,
                networkDataSource = networkDi.networkDataSource,
                isFirstLaunch = sharedPrefDi.preferenceDatasource
            )
        }

        val weatherDi by lazy { WeatherDi(networkDi.networkDataSource) }

    }

}