package com.example.fit_nutrition.profile.data.di

import com.example.fit_nutrition.app.ui.MainActivity
import com.example.fit_nutrition.profile.ui.profile.ProfileFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class,AppModule::class,ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: ProfileFragment)
    fun inject(activity: MainActivity)
    fun inject(application: MyApplication)
}