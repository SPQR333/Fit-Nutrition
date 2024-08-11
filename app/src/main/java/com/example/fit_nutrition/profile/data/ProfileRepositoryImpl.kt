package com.example.fit_nutrition.profile.data

import com.example.fit_nutrition.core.data.preferences.PreferencesManager
import com.example.fit_nutrition.profile.domain.ProfileRepository
import com.example.fit_nutrition.profile.domain.model.Profile

class ProfileRepositoryImpl(
    private val preferencesManager: PreferencesManager
) : ProfileRepository {
    override suspend fun saveProfile(profile: Profile) {
        preferencesManager.save(
            mapOf(
                KEY_AGE to profile.age,
                KEY_WEIGHT to profile.weight,
                KEY_HEIGHT to profile.height,
                KEY_GENDER to profile.gender
            )
        )
    }

    override fun readProfile(): Profile {
        return preferencesManager.getValues(listOf(KEY_AGE, KEY_GENDER, KEY_HEIGHT, KEY_WEIGHT))
            .let {
                Profile(
                    age = it.getOrDefault(KEY_AGE, ""),
                    gender = it.getOrDefault(KEY_GENDER, ""),
                    height = it.getOrDefault(KEY_HEIGHT, ""),
                    weight = it.getOrDefault(KEY_WEIGHT, ""),
                )
            }
    }

    private companion object {
        const val KEY_AGE: String = "AGE"
        const val KEY_WEIGHT: String = "WEIGHT"
        const val KEY_HEIGHT: String = "HEIGHT"
        const val KEY_GENDER: String = "GENDER"
    }
}