package com.first.database

import androidx.room.*
import com.first.database.model.CityEntity
import io.reactivex.Observable

@Dao
internal interface CityDao {

    @get:Query("SELECT COUNT(*) FROM CityEntity")
    val count: Int

    @Query("SELECT * FROM CityEntity ORDER BY favorite DESC, name ASC")
    fun cities(): Observable<List<CityEntity>>

    @Query("SELECT * FROM CityEntity WHERE name =:name")
    fun getByName(name: String): CityEntity?

    @Insert // Добавление элементов
    fun insert(cities: List<CityEntity>)

    @Insert // Добавление элемента
    fun insert(city: CityEntity)

    @Update // Обновление данных элемента
    fun update(city: CityEntity)//todo обновлять по фавориту и вэзе напрямую. это удалить

    @Delete // Удаление элемента
    fun delete(city: CityEntity)

    @Query("DELETE FROM CityEntity WHERE name = :name")
    fun delete(name: String)
}