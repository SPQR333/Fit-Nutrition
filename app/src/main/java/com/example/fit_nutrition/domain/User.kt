package com.example.fit_nutrition.domain

data class User(
    val id: Int,
    val isMale: Boolean,
    val name: String,
    val age: Int,
    val weight: Double,
    val height: Double
    )
