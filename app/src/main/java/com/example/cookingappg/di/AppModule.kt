package com.example.cookingappg.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.UserManager
import com.example.cookingappg.data.api.RecipeApi
import com.example.cookingappg.data.api.UserApi
import com.example.cookingappg.data.local_manager.LocalManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl("http:10.0.2.2:8080/api/v1/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalManager = LocalManager(application)

    @Provides
    @Singleton
    fun provideRecipeApi(): RecipeApi {
        return Retrofit.Builder()
            .baseUrl("http:10.0.2.2:8080/api/v1/recipes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}