package com.example.cookingappg.data.api

import com.example.cookingappg.data.User
import com.example.cookingappg.data.api.dto.AuthResponse
import com.example.cookingappg.data.api.dto.AvatarResponse
import com.example.cookingappg.data.api.dto.LogInRequest
import com.example.cookingappg.data.api.dto.SignUpRequest
import com.example.cookingappg.data.api.dto.UpdateUserDataRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface UserApi {
    @GET("{id}")
    suspend fun getUserData(@Path("id") id:Long): User

    @PUT("data")
    suspend fun updateUserData(@Body request: UpdateUserDataRequest)

    @PUT("image")
    @Multipart
    suspend fun updateAvatar(@Part("id") id: Long, @Part image: MultipartBody.Part): AvatarResponse

    @POST("sign_up")
    suspend fun signUp(@Body request: SignUpRequest): AuthResponse

    @POST("log_in")
    suspend fun logIn(@Body request: LogInRequest): AuthResponse
}