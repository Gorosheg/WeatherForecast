package com.first.weatherforecast

import android.app.Application
import androidx.room.Room
import com.first.weatherforecast.city.dI.CitiesDi
import com.first.weatherforecast.datasource.database.CitiesDatabase
import com.first.weatherforecast.weather.dI.WeatherDI

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, CitiesDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        lateinit var database: CitiesDatabase
        val citiesDi by lazy { CitiesDi(database) }
        val weatherDi by lazy { WeatherDI() }
    }

}