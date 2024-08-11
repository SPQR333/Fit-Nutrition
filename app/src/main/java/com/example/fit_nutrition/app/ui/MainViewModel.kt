package com.example.fit_nutrition.app.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MainViewModel : ViewModel() {
    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}