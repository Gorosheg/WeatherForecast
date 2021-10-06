package com.first.weatherforecast.database

import androidx.room.*
import com.first.weatherforecast.model.City

@Dao
interface CityDao {

    @Query("SELECT * FROM city")
    fun getAll(): MutableList<City>

    @Query("SELECT * FROM city WHERE id =:id")
    fun getById(id: Long): City

    @Insert // Добавление элемента
    fun insert(city: City)

    @Update // Обновление таблицы
    fun update(city: City)

    @Delete // Удаление элемента
    fun delete(city: City)
}