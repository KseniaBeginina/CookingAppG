package com.example.cookingappg.data.api

import com.example.cookingappg.data.api.dto.AuthResponse
import com.example.cookingappg.data.api.dto.LogInRequest
import com.example.cookingappg.data.api.dto.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("sign_up")
    suspend fun signUp(@Body request: SignUpRequest): AuthResponse

    @POST("log_in")
    suspend fun logIn(@Body request: LogInRequest): AuthResponse
}