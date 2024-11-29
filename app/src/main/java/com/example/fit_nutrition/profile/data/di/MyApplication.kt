package com.example.fit_nutrition.profile.data.di

import android.app.Application
import dagger.Component

class MyApplication : Application() {
 val appComponent: ApplicationComponent by lazy {
     DaggerApplicationComponent
         .builder()
         .appModule(AppModule(this))
         .build()
 }

    override fun onCreate() {
        super.onCreate()
    }
}


