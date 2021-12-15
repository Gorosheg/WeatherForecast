package com.first.sharedpreference

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceDi(context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences(
        MY_SETTINGS,  // Название таблицы
        Context.MODE_PRIVATE  // Модификатор доступа
    )

    private val preference = PreferenceDatasourceImpl(sharedPref)

    val mutablePreferenceDatasource: MutablePreferenceDatasource = preference
    val preferenceDatasource: PreferenceDatasource = preference

    companion object {
        private const val MY_SETTINGS = "MY_SETTINGS"
    }
}