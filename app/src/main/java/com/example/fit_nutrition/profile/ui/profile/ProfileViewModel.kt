package com.example.fit_nutrition.profile.ui.profile

import androidx.lifecycle.ViewModel
import com.example.fit_nutrition.profile.data.ProfileRepositoryImpl
import com.example.fit_nutrition.profile.domain.ProfileRepository
import com.example.fit_nutrition.profile.domain.model.Profile
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private lateinit var profileRepository: ProfileRepository

    val content: Flow<Profile?>
        get() = _content
    private val _content = MutableStateFlow<Profile?>(null)


    fun saveProfile(profile: Profile) {
    }


    fun readProfiles() {
        val profile = profileRepository.readProfile()
        _content.value = profile


    }


    public fun sync() {
        suspend fun sync() {
            coroutineScope {
                launch {
                    val profile = profileRepository.readProfile()
                    _content.value = profile

                }
            }
        }

    }
}


