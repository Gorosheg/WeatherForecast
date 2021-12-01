package com.first.weatherforecast.datasource.sharedPreference

import android.content.SharedPreferences

class PreferenceDatasourceImpl(private val sharedPref: SharedPreferences) :
    MutablePreferenceDatasource,
    PreferenceDatasource {

    // проверяем, первый ли раз открывается программа

    override var isFirstLaunchEnum: SharedPrefValues
        get() = SharedPrefValues.stringToEnum(
            sharedPref.getString(VISITED_KEY, null)
        ) // (ключ, значение)
        set(value) {
            sharedPref.edit().apply {
                putString(VISITED_KEY, value.enumToString())
                apply()
            }
        }


    override val isFirstLaunch: Boolean?
        get() =when (isFirstLaunchEnum) {
            SharedPrefValues.DEFAULT -> null
            SharedPrefValues.TRUE -> true
            SharedPrefValues.FALSE -> false
        }

    companion object {
        private const val VISITED_KEY = "VISITED_KEY"
    }


}
