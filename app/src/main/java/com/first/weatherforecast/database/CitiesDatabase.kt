package com.first.weatherforecast.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.first.weatherforecast.model.City

@Database(entities = [City::class], version = 1)
abstract class CitiesDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
}