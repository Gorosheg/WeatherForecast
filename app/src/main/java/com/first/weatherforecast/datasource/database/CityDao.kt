package com.first.weatherforecast.datasource.database

import androidx.room.*
import com.first.weatherforecast.datasource.database.model.CityEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CityDao {

    @Query("SELECT COUNT(*) FROM CityEntity")
    fun count(): Int

    @Query("SELECT * FROM CityEntity")
    fun getAll(): Observable<List<CityEntity>>

    @Query("SELECT * FROM CityEntity WHERE id =:id")
    fun getById(id: Long): Observable<CityEntity>

    @Insert // Добавление элементов
    fun insert(cities: List<CityEntity>)

    @Insert // Добавление элемента
    fun insert(city: CityEntity)

    @Update // Обновление данных элемента
    fun update(city: CityEntity)

    @Delete // Удаление элемента
    fun delete(city: CityEntity)

    @Query("DELETE FROM CityEntity WHERE name = :name")
    fun delete(name: String)
}