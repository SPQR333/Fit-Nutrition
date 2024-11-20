package com.example.fit_nutrition.profile.domain

import com.example.fit_nutrition.profile.domain.model.Profile
import dagger.Binds
import javax.inject.Inject


interface ProfileRepository  {
    suspend fun saveProfile(profile: Profile)
    suspend fun readProfile(): Profile


}