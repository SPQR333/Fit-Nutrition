package com.example.fit_nutrition.profile.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fit_nutrition.domain.CalculateCaloricDeficitUseCase
import com.example.fit_nutrition.profile.domain.ProfileRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ProfileViewModelFactory @Inject constructor(

    private val profileRepository: ProfileRepository,
    private val calculateCaloricDeficitUseCase: CalculateCaloricDeficitUseCase,
    private val viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as? T
            ?: throw IllegalArgumentException("Unknown ViewMidel class: $modelClass")
    }
}
