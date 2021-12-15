package com.first.sharedpreference

enum class FirstLaunchEnum {
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

        fun stringToEnum(string: String?): FirstLaunchEnum {
            return when (string) {
                null -> DEFAULT
                "True" -> TRUE
                else -> FALSE
            }
        }
    }
}