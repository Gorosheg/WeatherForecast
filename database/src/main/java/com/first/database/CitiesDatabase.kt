package com.first.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.first.database.model.CityEntity

@Database(entities = [CityEntity::class], version = 1)
internal abstract class CitiesDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
}