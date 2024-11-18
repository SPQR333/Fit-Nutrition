package com.example.fit_nutrition.profile.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fit_nutrition.profile.ui.profile.ProfileViewModel
import com.example.fit_nutrition.profile.ui.profile.ProfileViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    abstract fun bindViewModelFactory(factory: ProfileViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel



}