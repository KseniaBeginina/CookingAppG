package com.example.cookingappg

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cookingappg.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    prefs: SharedPreferences
): ViewModel(){
    var startDestination by mutableStateOf(Routes.AUTH)
        private set

    init {
        val id = prefs.getLong("userId", 0)
        if (id == 0L){
            startDestination = Routes.AUTH
        } else{
            startDestination = Routes.MENUDEST
        }
    }
}