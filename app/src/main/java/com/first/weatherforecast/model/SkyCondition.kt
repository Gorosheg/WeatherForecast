package com.first.weatherforecast.model

import androidx.annotation.StringRes
import com.first.weatherforecast.R

/**
 * enum - ограниченное перечисление.
 * Если перебирать его в when, то не нужна ветка else.
 * При вызове Enum не вызыается конструктор.
 * Enum - константа. При его вызове нельзя передавать свои параметры.
 */
enum class SkyCondition(@StringRes val text: Int) {

    CLEAR(R.string.clear),
    SNOW(R.string.snow),
    RAIN(R.string.rain),
    DRIZZLE(R.string.drizzle),
    THUNDERSTORM(R.string.thunderstorm),
    CLOUDS(R.string.clouds),
    MIST(R.string.mist),
    SMOKE(R.string.smoke),
    HAZE(R.string.haze),
    DUST(R.string.dust),
    FOG(R.string.fog),
    SAND(R.string.sand),
    ASH(R.string.ash),
    SQUALL(R.string.squall),
    TORNADO(R.string.tornado);

    companion object {

        fun buildSkyCondition(skyCondition: String): SkyCondition {
            return when (skyCondition) {
                "Clear" -> CLEAR
                "Snow" -> SNOW
                "Rain" -> RAIN
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
                else -> throw IllegalStateException("Unsupported sky condition: $skyCondition")
            }
        }

    }

}