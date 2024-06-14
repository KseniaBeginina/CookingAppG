package com.example.cookingappg.presentation.pages.authentication

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingappg.data.api.UserApi
import com.example.cookingappg.data.api.dto.AuthResponse
import com.example.cookingappg.data.api.dto.LogInRequest
import com.example.cookingappg.data.api.dto.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
data class AuthViewModel @Inject constructor(
    private val userApi: UserApi,
    private val prefs: SharedPreferences
):ViewModel(){
    fun signUp(email: String, name: String, password: String): Long {
        var response: AuthResponse? = null
        var error: Exception? = null
        runBlocking {
            try{
                response = userApi.signUp(
                    SignUpRequest(
                        email = email,
                        name = name,
                        password = password
                    )
                )
                prefs.edit().putLong("userId",response!!.id).apply()
                prefs.edit().putString("password", password).apply()
            } catch (e: Exception){
                error = e
            }
        }
        if (error != null) {
            throw error as Exception
        }
        else {
            return response!!.id
        }
    }

    fun logIn(email: String, password: String): Long {
        var response: AuthResponse? = null
        var error: Exception? = null
        runBlocking {
            try {
                response = userApi.logIn(
                    LogInRequest(
                        email = email,
                        password = password
                    )
                )
                prefs.edit().putLong("userId", response!!.id).apply()
                prefs.edit().putString("password", password).apply()
            } catch (e: Exception){
                error = e
            }
        }
        if (error != null) {
            throw error as Exception
        }
        else {
            return response!!.id
        }
    }
}
