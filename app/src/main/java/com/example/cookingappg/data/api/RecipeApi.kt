package com.example.cookingappg.data.api

import com.example.cookingappg.data.Recipe
import com.example.cookingappg.data.api.dto.AddRecipeRequest
import com.example.cookingappg.data.api.dto.AvatarResponse
import com.example.cookingappg.data.RecipePreview
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("byUserID/{id}")
    suspend fun getRecipePreview(@Path("id") id:Long): List<RecipePreview>

    @GET("{id}")
    suspend fun getRecipeDetails(@Path("id") id:Long): Recipe

    @GET("filter")
    suspend fun getWithFilters(
        @Query("userId") id: Long,
        @Query("query") query: String,
        @Query("categories") categories: List<String>,
        @Query("likedOnly") liked: Boolean,
        @Query("minTime") minTime: Int,
        @Query("maxTime") maxTime: Int,
    ): List<RecipePreview>

    @DELETE("{id}")
    suspend fun deleteRecipe(@Path("id") id:Long)

    @PUT("{id}")
    suspend fun updateRecipeData(@Path("id") id: Long, @Body recipe: AddRecipeRequest)
    @PUT("image")
    @Multipart
    suspend fun updateAvatar(@Part("id") id: Long, @Part image: MultipartBody.Part): AvatarResponse

    @PUT("like/{id}")
    suspend fun toggleLike(@Path("id") id:Long)

    @POST("create")
    @Multipart
    suspend fun addRecipe(@Part("recipe") recipe: AddRecipeRequest, @Part image: MultipartBody.Part)
}