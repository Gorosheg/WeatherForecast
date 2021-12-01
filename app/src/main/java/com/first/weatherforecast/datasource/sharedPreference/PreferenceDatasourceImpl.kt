package com.first.weatherforecast.datasource.sharedPreference

import android.content.SharedPreferences

class PreferenceDatasourceImpl(private val sharedPref: SharedPreferences) :
    MutablePreferenceDatasource,
    PreferenceDatasource {

    override var firstLaaunchEnum: FirstLaaunchEnum
        get() = FirstLaaunchEnum.stringToEnum(sharedPref.getString(VISITED_KEY, null))
        set(value) {
            sharedPref.edit().apply {
                putString(VISITED_KEY, value.enumToString())
                apply()
            }
        }


    override val isFirstLaunch: Boolean?
        get() = when (firstLaaunchEnum) {
            FirstLaaunchEnum.DEFAULT -> null
            FirstLaaunchEnum.TRUE -> true
            FirstLaaunchEnum.FALSE -> false
        }

    companion object {
        private const val VISITED_KEY = "VISITED_KEY"
    }


}
