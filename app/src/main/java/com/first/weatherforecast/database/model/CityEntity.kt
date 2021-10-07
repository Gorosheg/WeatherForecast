package com.first.weatherforecast.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.first.weatherforecast.ui.model.City

@Entity
class CityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val latitude: Double,
    val longitude: Double,
    var name: String
)

fun List<City>.toEntities(): List<CityEntity> = map { city ->
    city.toEntity()
}

fun City.toEntity() = CityEntity(
    latitude = latitude,
    longitude = longitude,
    name = name
)

// С ссылкой на функцию - map(CityEntity::toSimpleCity)
fun List<CityEntity>.toSimpleCities(): List<City> = map { city ->
    city.toSimpleCity()
}

fun CityEntity.toSimpleCity() = City(
    latitude = latitude,
    longitude = longitude,
    name = name
)
