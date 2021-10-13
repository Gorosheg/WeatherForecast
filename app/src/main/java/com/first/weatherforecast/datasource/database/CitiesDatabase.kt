package com.first.weatherforecast.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.first.weatherforecast.datasource.database.model.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class CitiesDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
}