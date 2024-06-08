package com.example.fit_nutrition.presentation
import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

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
