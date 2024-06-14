package com.example.cookingappg.data.api.dto

data class UpdateUserDataRequest (
    val id: Long,
    val name: String,
    val email: String,
    val password: String
)