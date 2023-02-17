package com.tossdesu.recipestest.domain.entity

data class Recipe(
    val id: String,
    val name: String,
    val headline: String,
    val description: String,
    val difficulty: Int,
    val calories: Int,
    val carbos: Int,
    val fats: Int,
    val proteins: Int,
    val image: String
)