package com.first.weatherforecast.model

import androidx.annotation.DrawableRes
import com.first.weatherforecast.R

enum class SkyImage(@DrawableRes val image: Int) {

    CLEAR(R.drawable.clear_sky),
    SNOW(R.drawable.snow),
    RAIN(R.drawable.rain),
    DRIZZLE(R.drawable.rain),
    THUNDERSTORM(R.drawable.thunderstorm),
    CLOUDS(R.drawable.cloudy),
    MIST(R.drawable.mist),
    SMOKE(R.drawable.mist),
    HAZE(R.drawable.mist),
    DUST(R.drawable.dust),
    FOG(R.drawable.mist),
    SAND(R.drawable.dust),
    ASH(R.drawable.ash),
    SQUALL(R.drawable.squall),
    TORNADO(R.drawable.tornado);

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