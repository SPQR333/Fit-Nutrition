package com.example.fit_nutrition.core.data.preferences

import android.content.SharedPreferences

class PreferencesManager(private val preferences: SharedPreferences) {

    fun save(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun save(key: String, value: Float) {
        preferences.edit().putFloat(key, value).apply()
    }

    fun save(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getValues(keys: List<String>): Map<String, String> = mutableMapOf<String, String>().apply {
        keys.forEach { put(it, preferences.getString(it, "") ?: "") }
    }

    fun getString(key: String): String {
        return preferences.getString(key, "") ?: ""
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key,0)
    }

    fun getFloat(key: String): Float {
        return preferences.getFloat(key,0f)
    }
}
