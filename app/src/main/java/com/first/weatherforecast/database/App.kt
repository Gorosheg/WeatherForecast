package com.first.weatherforecast.database

import android.app.Application
import androidx.room.Room

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(this, CitiesDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        lateinit var dataBase: CitiesDatabase
    }

}