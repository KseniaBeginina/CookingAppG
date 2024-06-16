package com.example.cookingappg.data.api.dto

import com.example.cookingappg.data.Product

data class AddRecipeRequest(
    val userId: Long,
    val name: String,
    val category: String,
    val cookTime: Int,
    val portions: Int,
    val calories: Float,
    val proteins: Float,
    val fats: Float,
    val carbos: Float,
    val recipeContent: String,
    val liked: Boolean,
    val products: List<Product>
)
