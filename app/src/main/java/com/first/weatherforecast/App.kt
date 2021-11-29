package com.first.weatherforecast

import android.annotation.SuppressLint
import android.app.Application
import com.first.weatherforecast.datasource.database.CitiesDatabase
import com.first.weatherforecast.datasource.database.DatabaseDi
import com.first.weatherforecast.datasource.network.NetworkDi
import com.first.weatherforecast.feature.city.dI.CitiesDi
import com.first.weatherforecast.feature.weather.dI.WeatherDi

@SuppressLint("StaticFieldLeak")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        databaseDi = DatabaseDi(this)
    }

    companion object {

        private lateinit var databaseDi: DatabaseDi
        private val networkDi by lazy { NetworkDi() }

        val citiesDi by lazy { CitiesDi(databaseDi.datasource, networkDi.networkDataSource) }
        val weatherDi by lazy { WeatherDi(networkDi.networkDataSource) }

    }

}