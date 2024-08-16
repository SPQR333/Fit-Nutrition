package com.example.fit_nutrition.profile.domain

import com.example.fit_nutrition.profile.domain.model.Profile

interface ProfileRepository {
    suspend fun saveProfile(profile: Profile)
    suspend fun readProfile(): Profile


}