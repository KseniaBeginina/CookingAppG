package com.example.cookingappg.data

data class Recipe(
    val userId: Long,
    val name: String,
    val category: String,
    val img: String,
    val cookTime: Int,
    val portions: Int,
    val calories: Float,
    val proteins: Float,
    val fats: Float,
    val carbos: Float,
    val rec: String,
    val liked: Boolean

)
