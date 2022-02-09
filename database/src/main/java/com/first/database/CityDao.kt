package com.first.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.first.database.model.CityEntity
import io.reactivex.Observable

@Dao
internal interface CityDao {

    @get:Query("SELECT COUNT(*) FROM CityEntity")
    val count: Int

    @Query("SELECT * FROM CityEntity ORDER BY favorite DESC, name ASC")
    fun cities(): Observable<List<CityEntity>>

    @Query("SELECT * FROM CityEntity WHERE name =:name")
    fun getByName(name: String): CityEntity

    @Query("SELECT * FROM CityEntity WHERE name =:name")
    fun checkForEmpty(name: String): CityEntity?

    @Query("UPDATE CityEntity SET favorite = :newFavorite WHERE name =:name")
    fun updateFavorite(newFavorite: Boolean, name: String)

    @Update // Обновление данных элемента
    fun update(city: CityEntity)

    @Query("DELETE FROM CityEntity WHERE name = :name")
    fun delete(name: String)

    @Insert // Добавление элемента
    fun insert(city: CityEntity)
}