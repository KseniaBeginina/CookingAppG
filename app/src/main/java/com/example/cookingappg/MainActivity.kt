package com.example.cookingappg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.cookingappg.navigation.NavGraph
import com.example.cookingappg.ui.theme.CookingAppGTheme
import com.example.cookingappg.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainVM by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookingAppGTheme {
                Surface (color = White) {
                    val startDestination = mainVM.startDestination
                    val navController = rememberNavController()
                    NavGraph(startDestination, navController = navController)
                }
            }
        }
    }
}