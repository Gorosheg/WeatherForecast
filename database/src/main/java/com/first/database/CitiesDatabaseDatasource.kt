package com.first.database

import com.first.common.model.City
import com.first.common.model.Weather
import com.first.database.model.*
import io.reactivex.Observable
import java.util.*

interface CitiesDatabaseDatasource {

    val isNoItems: Boolean

    fun addCity(city: City)

    fun isCityExist(cityName: String?): Boolean

    fun getCity(city: City): Weather?

    fun getAllCities(): Observable<List<City>>

    fun deleteCity(city: City)

    fun makeCityFavorite(city: City)

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
        val city: CityEntity? = cityDao.getByName(weather.cityName)

        if (city != null) {
            val newCity: CityEntity = city.copy(
                weather = weather.toEntity()
            )

            cityDao.update(newCity)
        }
    }

    override fun isCityExist(cityName: String?): Boolean {
        return if (cityName == null) false
        else cityDao.getByName(cityName) != null
    }

    override fun getCity(city: City): Weather? {
        val cityName = city.name
        if (cityName != null) {
            val saveCity = cityDao.getByName(cityName)?.weather?.toSimpleWeather()
            if (saveCity != null) {
                return saveCity
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

    override fun makeCityFavorite(city: City) {
        val oldCity: CityEntity? = city.name?.let(cityDao::getByName)

        if (oldCity != null) {
            val newCity: CityEntity = oldCity.copy(
                favorite = !city.favorite
            )

            cityDao.update(newCity)
        }
    }
}