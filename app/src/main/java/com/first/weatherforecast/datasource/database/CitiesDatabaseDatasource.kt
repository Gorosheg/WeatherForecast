package com.first.weatherforecast.datasource.database

import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.datasource.database.model.CityEntity
import com.first.weatherforecast.datasource.database.model.toEntity
import com.first.weatherforecast.datasource.database.model.toSimpleCities
import io.reactivex.Observable

class CitiesDatabaseDatasource(private val cityDao: CityDao) {

    private fun isEmpty(): Boolean {
        return cityDao.count() == 0
    }

    fun addCity(city: City) {
        cityDao.insert(city.toEntity())
    }

    fun isCityExist(city: City): Boolean {
        return if (city.name == null) false
        else {
            cityDao.getByName(city.name) != null
        }
    }

    fun getAllCities(): Observable<List<City>> {
        return cityDao.cities()
            .map(List<CityEntity>::toSimpleCities)
    }

    fun deleteCity(city: City) {
        if (city.name != null) {
            cityDao.delete(city.name)
        }
    }
}