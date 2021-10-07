package com.first.weatherforecast.database

import androidx.room.*
import com.first.weatherforecast.database.model.CityEntity

@Dao
interface CityDao {

    // TODO
//    @Query("")
//    fun isEmpty(): Boolean

    @Query("SELECT * FROM CityEntity")
    fun getAll(): List<CityEntity>

    @Query("SELECT * FROM CityEntity WHERE id =:id")
    fun getById(id: Long): CityEntity

    @Insert // Добавление элемента
    fun insert(cities: List<CityEntity>)

    @Insert // Добавление элемента
    fun insert(city: CityEntity)

    @Update // Обновление таблицы
    fun update(city: CityEntity)

    @Delete // Удаление элемента
    fun delete(city: CityEntity)
}