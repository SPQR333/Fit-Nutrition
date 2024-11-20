package com.example.fit_nutrition.profile.data.di

import com.example.fit_nutrition.profile.data.ProfileRepositoryImpl
import com.example.fit_nutrition.profile.domain.ProfileRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun BindProfileRepository(
        profileRepositoryImpl: ProfileRepositoryImpl
    ) :ProfileRepository


}