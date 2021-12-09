package com.first.weatherforecast.datasource.database

import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.datasource.database.model.CityEntity
import com.first.weatherforecast.datasource.database.model.toEntity
import com.first.weatherforecast.datasource.database.model.toSimpleCities
import io.reactivex.Observable

class CitiesDatabaseDatasource(private val cityDao: CityDao/*, firstCity: City*/) {

   /* init {
        if (isEmpty()) {
            cityDao.insert(firstCity.toEntity())
        }
    }*/

    private fun isEmpty(): Boolean {
        return cityDao.count() == 0
    }

/*    private fun buildInitialCities(): List<CityEntity> {
        return listOf(
            CityEntity(latitude = 59.939999, longitude = 30.315877, name = "Санкт-Петербург"),
            CityEntity(latitude = 55.7522, longitude = 37.6156, name = "Москва"),
            CityEntity(latitude = 60.1695, longitude = 24.9354, name = "Хельсинки")
        )
    }*/

    fun addCity(city: City) {
        cityDao.insert(city.toEntity())
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