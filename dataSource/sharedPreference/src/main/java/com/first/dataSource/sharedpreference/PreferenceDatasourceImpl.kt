package com.first.dataSource.sharedpreference

import android.content.SharedPreferences

internal class PreferenceDatasourceImpl(private val sharedPref: SharedPreferences) :
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


    override val isFirstLaunch: Boolean
        get() = when (firstLaunchEnum) {
            FirstLaunchEnum.TRUE -> true
            else -> false
        }

    companion object {

        private const val VISITED_KEY = "VISITED_KEY"

    }

}
