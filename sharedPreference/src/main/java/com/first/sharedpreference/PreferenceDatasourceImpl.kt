package com.first.sharedpreference

import android.content.SharedPreferences

class PreferenceDatasourceImpl(private val sharedPref: SharedPreferences) :
    MutablePreferenceDatasource,
    PreferenceDatasource {

    override var firstLaunchEnum: FirstLaunchEnum
        get() = FirstLaunchEnum.stringToEnum(sharedPref.getString(VISITED_KEY, null))
        set(value) {
            sharedPref.edit().apply {
                putString(VISITED_KEY, value.enumToString())
                apply()
            }
        }


    override val isFirstLaunch: Boolean?
        get() = when (firstLaunchEnum) {
            FirstLaunchEnum.DEFAULT -> null
            FirstLaunchEnum.TRUE -> true
            FirstLaunchEnum.FALSE -> false
        }

    companion object {
        private const val VISITED_KEY = "VISITED_KEY"
    }


}
