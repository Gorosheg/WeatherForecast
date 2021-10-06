package com.first.weatherforecast.database

import com.first.weatherforecast.model.City

class CityListDataChanged {

    private val db = DatabaseHolder.dataBase
    private val cityDao = db.cityDao

    fun addCity(city: City) {
        cityDao.insert(city)
    }

    fun allCities(): List<City> {
        return cityDao.getAll()
    }

    fun deleteCity(city: City) {
        cityDao.delete(city)
    }
}