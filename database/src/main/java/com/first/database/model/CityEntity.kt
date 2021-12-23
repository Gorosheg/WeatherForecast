package com.first.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.first.common.model.City
import com.first.common.model.Coordinates

@Entity
internal data class CityEntity(
    val latitude: Double, // TODO: чек интернета
    val longitude: Double, // TODO: отображение старых или новых данных в зависимости от интернета
    @PrimaryKey
    val name: String,
    @Embedded
    val weather: WeatherEntity?
)

internal fun List<City>.toEntities(): List<CityEntity> = map { city ->
    city.toEntity()
}

internal fun City.toEntity() = CityEntity(
    latitude = coordinates?.latitude ?: 0.0,
    longitude = coordinates?.longitude ?: 0.0,
    name = name ?: "",
    weather = null
)

// С ссылкой на функцию - map(CityEntity::toSimpleCity)
internal fun List<CityEntity>.toSimpleCities(): List<City> = map { city ->
    city.toSimpleCity()
}

internal fun CityEntity.toSimpleCity() = City(
    coordinates = Coordinates(latitude, longitude),
    name = name
)
