package com.first.weatherforecast.datasource.database

import android.content.Context
import androidx.room.Room
import com.first.weatherforecast.common.model.City

class DatabaseDi(context: Context) {

    private val database: CitiesDatabase =
        Room.databaseBuilder(context, CitiesDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()

    private val cityDao: CityDao = database.cityDao

    val datasource = CitiesDatabaseDatasource(cityDao)

}