package com.first.weatherforecast

import android.annotation.SuppressLint
import android.app.Application
import com.first.citiesscreen.dI.CitiesDi
import com.first.database.DatabaseDi
import com.first.network.NetworkDi
import com.first.sharedpreference.FirstLaunchEnum
import com.first.sharedpreference.SharedPreferenceDi
import com.first.weatherscreen.dI.WeatherDi

@SuppressLint("StaticFieldLeak")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        databaseDi = DatabaseDi(this)
        sharedPrefDi = SharedPreferenceDi(this)

        WeatherDi.instance = WeatherDi(networkDi.networkDataSource)

        val navigator = NavigatorImpl()

        CitiesDi.instance = CitiesDi(
            datasource = databaseDi.datasource,
            networkDataSource = networkDi.networkDataSource,
            preferenceDatasource = sharedPrefDi.preferenceDatasource,
            navigator = navigator
        )

        initIsFirstLaunchPref(sharedPrefDi.mutablePreferenceDatasource)
    }

    private fun initIsFirstLaunchPref(preferences: com.first.sharedpreference.MutablePreferenceDatasource) {
        if (preferences.firstLaunchEnum == FirstLaunchEnum.DEFAULT) {
            preferences.firstLaunchEnum = FirstLaunchEnum.TRUE
        } else if (preferences.firstLaunchEnum == FirstLaunchEnum.TRUE) {
            preferences.firstLaunchEnum = FirstLaunchEnum.FALSE
        }
    }

    companion object {

        private lateinit var sharedPrefDi: SharedPreferenceDi
        private lateinit var databaseDi: DatabaseDi
        private val networkDi by lazy { NetworkDi() }

    }

}