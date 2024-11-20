package com.example.fit_nutrition.domain

import com.example.fit_nutrition.profile.domain.ProfileRepository
import com.example.fit_nutrition.profile.domain.model.Profile
import dagger.Binds
import javax.inject.Inject


class CalculateCaloricDeficitUseCase @Inject constructor(
   private val repository: ProfileRepository
) {
    fun execute(profile: Profile): Double {
        val isMale = profile.gender == "Male"
        // Рассчет калорийного дефицита (примерный)
        val bmr = if (isMale) {
            (10 * profile.weight) + (6.25 * profile.height) - (5 * profile.age) - 161
        } else {
            (10 * profile.weight) + (6.25 * profile.height) - (5 * profile.age) - 5
        }

        return bmr - 500 // 500 ккал дефицит для похудения
    }
}