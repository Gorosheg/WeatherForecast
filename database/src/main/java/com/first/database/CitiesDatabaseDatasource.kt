package com.first.database

import com.first.database.model.CityEntity
import com.first.database.model.toSimpleCities
import com.first.common.model.City
import io.reactivex.Observable

class CitiesDatabaseDatasource(private val cityDao: CityDao) {

    private fun isEmpty(): Boolean {
        return cityDao.count() == 0
    }

    fun addCity(city: City) {
        cityDao.insert(city.toEntity())
    }

    fun isCityExist(city: City): Boolean {
        val cityName = city.name
        return if (cityName == null) false
        else cityDao.getByName(cityName) != null

    }

    fun getAllCities(): Observable<List<City>> {
        return cityDao.cities()
            .map(List<CityEntity>::toSimpleCities)
    }

    fun deleteCity(city: City) {
        val cityName = city.name
        if (cityName != null) {
            cityDao.delete(cityName)
        }
    }
}