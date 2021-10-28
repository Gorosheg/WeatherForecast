package com.first.weatherforecast.datasource.database

import androidx.room.*
import com.first.weatherforecast.datasource.database.model.CityEntity

@Dao
interface CityDao {

    @Query("SELECT COUNT(*) FROM CityEntity")
    fun count(): Int

    @Query("SELECT * FROM CityEntity")
    fun getAll(): List<CityEntity>

    @Query("SELECT * FROM CityEntity WHERE id =:id")
    fun getById(id: Long): CityEntity

    @Insert // Добавление элементов
    fun insert(cities: List<CityEntity>)

    @Insert // Добавление элемента
    fun insert(city: CityEntity)

    @Update // Обновление данных элемента
    fun update(city: CityEntity)

    @Delete // Удаление элемента
    fun delete(city: CityEntity)
}