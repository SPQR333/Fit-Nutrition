package com.example.fit_nutrition.profile.data.di

import android.app.Application

class MyApplication : Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent
    }
}