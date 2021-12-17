package com.first.database

import androidx.room.*
import com.first.database.model.CityEntity
import io.reactivex.Observable

@Dao
internal interface CityDao {

    @Query("SELECT COUNT(*) FROM CityEntity")
    fun count(): Int

    @Query("SELECT * FROM CityEntity")
    fun cities(): Observable<List<CityEntity>>

    @Query("SELECT * FROM CityEntity WHERE name =:name")
    fun getByName(name: String): CityEntity?

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