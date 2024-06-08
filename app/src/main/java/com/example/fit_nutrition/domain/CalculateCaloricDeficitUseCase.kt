package com.example.fit_nutrition.domain

class CalculateCaloricDeficitUseCase {
    fun execute(age: Int, weight: Float, height: Float, isMale: Boolean): Double {
        // Рассчет калорийного дефицита (примерный)
        val bmr = if (isMale) {
            (10 * weight) + (6.25 * height) - (5 * age) - 161
        } else {
            (10 * weight) + (6.25 * height) - (5 * age) - 5
        }

        return bmr - 500 // 500 ккал дефицит для похудения
    }
}