package com.example.fit_nutrition.presentation

import androidx.lifecycle.ViewModel
import com.example.fit_nutrition.domain.CalculateCaloricDeficitUseCase

class CaloricDeficitViewModel(private val calculateCaloricDeficitUseCase: CalculateCaloricDeficitUseCase) : ViewModel() {

    fun calculateCaloricDeficit(age: Int, weight: Float, height: Float, isMale: Boolean): Double {
        return calculateCaloricDeficitUseCase.execute(age, weight, height, isMale)
    }
}