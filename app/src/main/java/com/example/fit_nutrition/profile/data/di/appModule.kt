package com.example.fit_nutrition.profile.data.di

import android.app.Application
import android.content.Context
import dagger.Provides
import javax.inject.Singleton

class appModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application.applicationContext


}