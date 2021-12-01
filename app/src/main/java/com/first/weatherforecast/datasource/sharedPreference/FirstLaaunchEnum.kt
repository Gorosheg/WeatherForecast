package com.first.weatherforecast.datasource.sharedPreference

enum class FirstLaaunchEnum {
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

        fun stringToEnum(string: String?): FirstLaaunchEnum {
            return when (string) {
                null -> DEFAULT
                "True" -> TRUE
                else -> FALSE
            }
        }
    }
}