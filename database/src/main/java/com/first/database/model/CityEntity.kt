package com.first.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.first.common.model.City
import com.first.common.model.Coordinates

@Entity
data class CityEntity(
    val latitude: Double,
    val longitude: Double,
    @PrimaryKey
    val name: String
)

fun List<City>.toEntities(): List<CityEntity> = map { city ->
    city.toEntity()
}

fun City.toEntity() = CityEntity(
    latitude = coordinates?.latitude ?: 0.0,
    longitude = coordinates?.longitude ?: 0.0,
    name = name ?: ""
)

// С ссылкой на функцию - map(CityEntity::toSimpleCity)
fun List<CityEntity>.toSimpleCities(): List<City> = map { city ->
    city.toSimpleCity()
}

fun CityEntity.toSimpleCity() = City(
    coordinates = Coordinates(latitude, longitude),
    name = name
)
