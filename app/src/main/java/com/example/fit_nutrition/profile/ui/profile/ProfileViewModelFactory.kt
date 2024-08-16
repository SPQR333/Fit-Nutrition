package com.example.fit_nutrition.profile.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fit_nutrition.domain.CalculateCaloricDeficitUseCase
import com.example.fit_nutrition.profile.domain.ProfileRepository

class ProfileViewModelFactory(
    private val profileRepository: ProfileRepository,
    private val calculateCaloricDeficitUseCase: CalculateCaloricDeficitUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ProfileViewModel(profileRepository, calculateCaloricDeficitUseCase) as T
    }
}
