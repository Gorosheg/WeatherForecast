package com.first.citiesscreen.data

import com.first.common.model.City
import com.first.common.model.Coordinates
import com.first.common.model.Weather
import com.first.database.CitiesDatabaseDatasource
import com.first.network.WeatherDatasource
import com.first.sharedpreference.PreferenceDatasource
import io.reactivex.Observable
import io.reactivex.Single

internal class CitiesRepositoryImpl(
    private val database: CitiesDatabaseDatasource,
    private val networkDataSource: WeatherDatasource,
    private val PreferenceDatasource: PreferenceDatasource
) : CitiesRepository {

    override val cities: Observable<List<City>>
        get() = database.getAllCities()

    override fun loadWeather(city: City): Single<Weather> {
        return networkDataSource.loadingWeather(city).doOnSuccess {
            val newCity = copyCity(city, it)
            if (!isCityExist(newCity)) {
                addCity(newCity)
                saveWeather(it)
            }
        }
    }

    private fun copyCity(
        city: City,
        response: Weather
    ): City {
        return if (city.name == null) {
            city.copy(
                name = response.cityName
            )
        } else {
            city.copy(
                coordinates = Coordinates(response.latitude, response.longitude)
            )
        }
    }

    override fun isCityExist(city: City): Boolean {
        return database.isCityExist(city.name)
    }

    override fun addCity(city: City) {
        database.addCity(city)
    }

    private fun saveWeather(weather: Weather) {
        database.update(weather)
    }

    override fun removeCity(city: City) {
        database.deleteCity(city)
    }

    override fun isEmpty(): Boolean {
        return database.isEmpty()
    }

    override fun isFirstLaunch(): Boolean? {
        return PreferenceDatasource.isFirstLaunch

    }
}
