package com.example.cookingappg

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingappg.data.local_manager.LocalManager
import com.example.cookingappg.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    prefs: SharedPreferences,
    localManager: LocalManager
): ViewModel(){
    var startDestination by mutableStateOf(Routes.AUTH)

    init {
//        localManager.readAppEntry().onEach { shouldStartFromHomeScreen ->
//            startDestination = if (shouldStartFromHomeScreen) {
//                Routes.MENUDEST
//            } else {
//                Routes.AUTH
//            }
//        }.launchIn(viewModelScope)
        val id = prefs.getLong("userId", 0)
        startDestination = if (id == 0L) {
            Routes.AUTH
        } else {
            Routes.MENUDEST
        }
    }
}