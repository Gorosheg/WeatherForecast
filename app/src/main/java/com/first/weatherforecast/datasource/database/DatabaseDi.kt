package com.first.weatherforecast.datasource.database

import android.content.Context
import androidx.room.Room

class DatabaseDi(context: Context) {

    private val database: CitiesDatabase =
        Room.databaseBuilder(context, CitiesDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()

    private val cityDao: CityDao = database.cityDao

    val datasource = CitiesDatabaseDatasource(cityDao/*, firstCity*/)

    /* fun qwe(firstCity: City): CitiesDatabaseDatasource {
         return CitiesDatabaseDatasource(cityDao, firstCity)
     }*/
}