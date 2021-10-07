package com.first.weatherforecast.database

import com.first.weatherforecast.database.model.CityEntity
import com.first.weatherforecast.database.model.toEntity
import com.first.weatherforecast.database.model.toSimpleCities
import com.first.weatherforecast.ui.model.City

class CitiesDatabaseDatasource {

    private val db = App.dataBase
    private val cityDao = db.cityDao

    init {
        if (isEmpty()) {
            cityDao.insert(buildInitialCities())
        }
    }

    private fun buildInitialCities(): List<CityEntity> {
        return listOf(
            CityEntity(latitude = 59.939999, longitude = 30.315877, name = "Санкт-Петербург"),
            CityEntity(latitude = 55.7522, longitude = 37.6156, name = "Москва"),
            CityEntity(latitude = 60.1695, longitude = 24.9354, name = "Хельсинки")
        )
    }

    fun addCity(city: City) {
        cityDao.insert(city.toEntity())
    }


    private fun isEmpty(): Boolean {
        return cityDao.count() == 0
    }

    fun getAllCities(): List<City> {
        return cityDao.getAll().toSimpleCities()
    }

    fun deleteCity(city: City) {
        cityDao.delete(city.toEntity())
    }
}