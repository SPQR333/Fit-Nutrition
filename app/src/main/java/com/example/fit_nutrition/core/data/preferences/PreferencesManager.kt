package com.example.fit_nutrition.core.data.preferences

import android.content.SharedPreferences

class PreferencesManager(private val preferences: SharedPreferences) {

    fun save(keyValue: Map<String, String>) {
        preferences.edit().apply {
            keyValue.entries.forEach { putString(it.key, it.value) }
        }.apply()
    }

    fun getValues(keys: List<String>): Map<String, String> = mutableMapOf<String, String>().apply {
        keys.forEach { put(it, preferences.getString(it, "") ?: "") }
    }


    fun saveData(age: String, weight: String, height: String, gender: String) {
        
        val editor = preferences.edit()
        editor.putString("AGE", age)
        editor.putString("WEIGHT", weight)
        editor.putString("HEIGHT", height)
        editor.putString("GENDER", gender)
        editor.apply()
    }

    fun loadData(): Map<String, String> {
        val age = preferences.getString("AGE", "") ?: ""
        val weight = preferences.getString("WEIGHT", "") ?: ""
        val height = preferences.getString("HEIGHT", "") ?: ""
        val gender = preferences.getString("GENDER", "Male") ?: "Male"
        return mapOf("AGE" to age, "WEIGHT" to weight, "HEIGHT" to height, "GENDER" to gender)
    }
}
