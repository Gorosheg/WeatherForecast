package com.first.dataSource.database

import com.first.common.model.City
import com.first.common.model.Weather
import com.first.dataSource.database.model.CityEntity
import com.first.dataSource.database.model.toEntity
import com.first.dataSource.database.model.toSimpleCities
import com.first.dataSource.database.model.toSimpleWeather
import io.reactivex.Observable
import java.util.*

interface CitiesDatabaseDatasource {

    val isNoItems: Boolean

    fun addCity(city: City)

    fun isCityExist(cityName: String?): Boolean

    fun getCity(city: City): Weather?

    fun getAllCities(): Observable<List<City>>

    fun deleteCity(city: City)

    fun changeFavoriteState(city: City)

    fun update(weather: Weather)
}

internal class CitiesDatabaseDatasourceImpl(
    private val cityDao: CityDao
) : CitiesDatabaseDatasource {

    override val isNoItems: Boolean
        get() = cityDao.count == 0

    override fun addCity(city: City) {
        cityDao.insert(city.toEntity())
    }

    override fun update(weather: Weather) {
        val city: CityEntity = cityDao.getByName(weather.cityName)

        val newCity: CityEntity = city.copy(
            weather = weather.toEntity()
        )

        cityDao.update(newCity)
    }

    override fun isCityExist(cityName: String?): Boolean {
        return if (cityName == null) false
        else cityDao.checkForEmpty(cityName) != null
    }

    override fun getCity(city: City): Weather? {
        val cityName = city.name
        if (cityName != null) {
            val requiredCity = cityDao.getByName(cityName).weather?.toSimpleWeather()
            if (requiredCity != null) {
                return requiredCity
            }
        }
        return null
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

    override fun changeFavoriteState(city: City) {
        val cityName = city.name
        if (cityName != null) {
            cityDao.updateFavorite(!city.favorite, cityName)
        }
    }
}