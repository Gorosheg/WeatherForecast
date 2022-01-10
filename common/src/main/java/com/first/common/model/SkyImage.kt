package com.first.common.model

import androidx.annotation.DrawableRes
import com.first.common.R

enum class SkyImage(@DrawableRes val image: Int ) {

    CLEAR(R.drawable.ic_sun),
    SNOW(R.drawable.ic_snow),
    RAIN(R.drawable.ic_rain),
    DRIZZLE(R.drawable.ic_rain),
    THUNDERSTORM(R.drawable.ic_thunderstorm),
    CLOUDS(R.drawable.ic_cloud),
    MIST(R.drawable.ic_mist),
    SMOKE(R.drawable.ic_mist),
    HAZE(R.drawable.ic_mist),
    DUST(R.drawable.ic_mist),
    FOG(R.drawable.ic_mist),
    SAND(R.drawable.ic_sandstorm),
    ASH(R.drawable.ic_ash),
    SQUALL(R.drawable.ic_squal),
    TORNADO(R.drawable.ic_tornado);

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