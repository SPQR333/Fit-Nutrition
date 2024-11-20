package com.example.fit_nutrition.profile.data

import com.example.fit_nutrition.core.data.preferences.PreferencesManager
import com.example.fit_nutrition.profile.domain.ProfileRepository
import com.example.fit_nutrition.profile.domain.model.Profile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ProfileRepository {
    override suspend fun saveProfile(profile: Profile) {
        preferencesManager.save(KEY_AGE, profile.age)
        preferencesManager.save(KEY_WEIGHT, profile.weight)
        preferencesManager.save(KEY_HEIGHT, profile.height)
        preferencesManager.save(KEY_GENDER, profile.gender)
    }

    override suspend fun readProfile(): Profile {
        val age = preferencesManager.getInt(KEY_AGE)
        val weight = preferencesManager.getFloat(KEY_WEIGHT)
        val height = preferencesManager.getFloat(KEY_HEIGHT)
        val gender = preferencesManager.getString(KEY_GENDER)

        return Profile(
            age = age,
            weight = weight,
            height = height,
            gender = gender
        )
    }

    private companion object {
        private const val KEY_AGE: String = "AGE"
        private const val KEY_WEIGHT: String = "WEIGHT"
        private const val KEY_HEIGHT: String = "HEIGHT"
        private const val KEY_GENDER: String = "GENDER"
    }
}