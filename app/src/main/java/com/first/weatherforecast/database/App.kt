package com.first.weatherforecast.database

import android.app.Application
import androidx.room.Room

class App : Application() {

    init {
        println("qwerty App init")
    }
    override fun onCreate() {
        super.onCreate()
        println("qwerty App on create")
        dataBase = Room.databaseBuilder(this, CitiesDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        lateinit var dataBase: CitiesDatabase
    }

}