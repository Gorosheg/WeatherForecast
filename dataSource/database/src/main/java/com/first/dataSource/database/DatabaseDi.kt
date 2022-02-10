package com.first.dataSource.database

import android.content.Context
import androidx.room.Room

class DatabaseDi(context: Context) {

    private val database: CitiesDatabase =
        Room.databaseBuilder(context, CitiesDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()

    private val cityDao: CityDao = database.cityDao

    val datasource: CitiesDatabaseDatasource = CitiesDatabaseDatasourceImpl(cityDao)

}