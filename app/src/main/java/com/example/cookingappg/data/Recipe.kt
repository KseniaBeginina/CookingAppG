package com.example.cookingappg.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val userId: Long,
    val name: String,
    val category: String,
    val image: String,
    val cookTime: Int,
    val portions: Int,
    val calories: Float,
    val proteins: Float,
    val fats: Float,
    val carbos: Float,
    val recipeContent: String,
    val liked: Boolean,
    val products: List<Product>
) : Parcelable
