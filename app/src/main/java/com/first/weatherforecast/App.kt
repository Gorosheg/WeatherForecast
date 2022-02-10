package com.first.weatherforecast

import android.annotation.SuppressLint
import android.app.Application
import com.first.feature.citiesscreen.dI.CitiesDi
import com.first.dataSource.database.DatabaseDi
import com.first.dataSource.network.NetworkDi
import com.first.dataSource.sharedpreference.FirstLaunchEnum
import com.first.dataSource.sharedpreference.MutablePreferenceDatasource
import com.first.dataSource.sharedpreference.SharedPreferenceDi
import com.first.feature.weatherScreen.dI.WeatherDi

@SuppressLint("StaticFieldLeak")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        databaseDi = DatabaseDi(this)
        sharedPrefDi = SharedPreferenceDi(this)

        val navigator = NavigatorImpl()
        NavigatorDi.instance = NavigatorDi(navigator)

        WeatherDi.instance = WeatherDi(networkDi.networkDataSource, databaseDi.datasource, navigator)

        CitiesDi.instance = CitiesDi(
            datasource = databaseDi.datasource,
            networkDataSource = networkDi.networkDataSource,
            preferenceDatasource = sharedPrefDi.preferenceDatasource,
            cityNavigator = navigator
        )

        initIsFirstLaunchPref(sharedPrefDi.mutablePreferenceDatasource)
    }

    private fun initIsFirstLaunchPref(preferences: MutablePreferenceDatasource) {
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