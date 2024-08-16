package com.example.fit_nutrition.profile.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fit_nutrition.domain.CalculateCaloricDeficitUseCase
import com.example.fit_nutrition.profile.data.ProfileRepositoryImpl
import com.example.fit_nutrition.profile.domain.ProfileRepository
import com.example.fit_nutrition.profile.domain.model.Profile
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val calculateCaloricDeficitUseCase: CalculateCaloricDeficitUseCase

) : ViewModel() {

    val content: Flow<Profile?>
        get() = _content
    private val _content = MutableStateFlow<Profile?>(null)

    val contRes: Flow<String?>
        get() = _contRes
    private val _contRes = MutableStateFlow<String?>(null)
    fun saveProfile(profile: Profile) {
        viewModelScope.launch {
            profileRepository.saveProfile(profile)
        }
        sync()
    }

    fun sync() {
        viewModelScope.launch {
            val profile = profileRepository.readProfile()
            _content.value = profile
            calculateCaloricDeficitUseCase.execute(profile)
        }
    }


    fun calculateCalories(profile: Profile) {
        viewModelScope.launch {
            val result = calculateCaloricDeficitUseCase.execute(profile)
            _contRes.value = result.toString()
        }
    }


}


