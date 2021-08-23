package com.first.weatherforecast.model

import androidx.annotation.DrawableRes
import com.first.weatherforecast.R

enum class SkyImage(@DrawableRes val image: Int) {

    CLEAR(R.drawable.clear_sky),
    SNOW(R.drawable.snow),
    RAIN(R.drawable.rain),
    DRIZZLE(R.drawable.rain),
    THUNDERSTORM(R.drawable.rain),
    CLOUDS(R.drawable.cloudy),
    MIST(R.drawable.rain),
    SMOKE(R.drawable.rain),
    HAZE(R.drawable.rain),
    DUST(R.drawable.rain),
    FOG(R.drawable.rain),
    SAND(R.drawable.rain),
    ASH(R.drawable.rain),
    SQUALL(R.drawable.rain),
    TORNADO(R.drawable.rain);

    companion object {

        fun buildSkyImage(skyCondition: String): SkyImage {
            return when (skyCondition) {
                "Clear" -> CLEAR
                "Rain" -> RAIN
                "Snow" -> SNOW
                "Drizzle" -> DRIZZLE
                "Thunderstorm" -> THUNDERSTORM
                "Clouds" -> CLOUDS
                "Mist" -> MIST
                "Smoke" -> SMOKE
                "Haze" -> HAZE
                "Dust" -> DUST
                "Fog" -> FOG
                "Sand" -> SAND
                "Ash" -> ASH
                "Squall" -> SQUALL
                "Tornado" -> TORNADO
                else -> throw IllegalStateException("Unsupported sky condition: $SkyImage")
            }
        }

    }

}