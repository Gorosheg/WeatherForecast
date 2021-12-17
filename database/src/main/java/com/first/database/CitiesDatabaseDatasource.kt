package com.first.database

import com.first.common.model.City
import com.first.database.model.CityEntity
import com.first.database.model.toEntity
import com.first.database.model.toSimpleCities
import io.reactivex.Observable

interface CitiesDatabaseDatasource {

    fun isEmpty(): Boolean

    fun addCity(city: City)

    fun isCityExist(city: City): Boolean

    fun getAllCities(): Observable<List<City>>

    fun deleteCity(city: City)
}

internal class CitiesDatabaseDatasourceImpl(
    private val cityDao: CityDao
) : CitiesDatabaseDatasource {

    override fun isEmpty(): Boolean {
        return cityDao.count() == 0
    }

    override fun addCity(city: City) {
        cityDao.insert(city.toEntity())
    }

    override fun isCityExist(city: City): Boolean {
        val cityName = city.name
        return if (cityName == null) false
        else cityDao.getByName(cityName) != null
    }

    override fun getAllCities(): Observable<List<City>> {
        return cityDao.cities()
            .map(List<CityEntity>::toSimpleCities)
    }

    override fun deleteCity(city: City) {
        val cityName = city.name
        if (cityName != null) {
            cityDao.delete(cityName)
        }
    }
}