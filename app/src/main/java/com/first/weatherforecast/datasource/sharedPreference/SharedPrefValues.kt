package com.first.weatherforecast.datasource.sharedPreference

enum class SharedPrefValues {
    DEFAULT,
    TRUE,
    FALSE;

    fun enumToString(): String? {
        return when (this) {
            DEFAULT -> null
            TRUE -> "True"
            FALSE -> "False"
        }
    }

    companion object {

        fun stringToEnum(string: String?): SharedPrefValues {
            return when (string) {
                null -> DEFAULT
                "True" -> TRUE
                else -> FALSE
            }
        }
    }
}