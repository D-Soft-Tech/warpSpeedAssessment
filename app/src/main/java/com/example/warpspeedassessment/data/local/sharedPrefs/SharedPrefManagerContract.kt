package com.example.warpspeedassessment.data.local.sharedPrefs

import com.dsofttech.dprefs.enums.DPrefsDefaultValue

interface SharedPrefManagerContract {
    fun retrieveStringFromSharedPrefs(
        key: String,
        defaultValue: String = DPrefsDefaultValue.DEFAULT_VALUE_STRING.value as String,
    ): String

    fun retrieveIntFromSharedPrefs(key: String, defaultValue: Int? = null): Int
    fun saveStringToSharedPrefs(key: String, value: String)
    fun saveIntToSharedPrefs(key: String, value: Int)
    fun saveBooleanToSharedPrefs(key: String, value: Boolean)
    fun saveLongToSharedPrefs(key: String, value: Long)
    fun saveDoubleToSharedPrefs(key: String, value: Double)
    fun retrieveBooleanFromSharedPrefs(key: String, defaultValue: Boolean = false): Boolean
    fun retrieveLongFromSharedPrefs(key: String, defaultValue: Long? = null): Long
    fun retrieveDoubleFromSharedPrefs(key: String, defaultValue: Double? = null): Double
    fun removePref(key: String)
}