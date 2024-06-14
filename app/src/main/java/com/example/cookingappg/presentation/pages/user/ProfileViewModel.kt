package com.example.cookingappg.presentation.pages.user

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.cookingappg.data.api.UserApi
import com.example.cookingappg.data.local_manager.LocalManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userApi: UserApi,
    private val prefs: SharedPreferences,
    private val localManager: LocalManager
): ViewModel(){
    fun logOut(){
        runBlocking {
            localManager.removeAppEntry()
            prefs.edit().remove("userId").apply()
        }
    }
}