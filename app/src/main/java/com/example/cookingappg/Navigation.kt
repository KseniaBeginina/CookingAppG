package com.example.cookingappg

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cookingappg.pages.Menu
import com.example.cookingappg.pages.Login
import com.example.cookingappg.pages.Registration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun NavGraph(navController: NavHostController){
    val auth = Firebase.auth
    val startDestination = if (auth.currentUser == null) {
        Routes.LOGIN
    } else {
        Log.d("Start","${auth.currentUser?.email}")
        Routes.MENU
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        composable(Routes.LOGIN) {
            Login(){
                    route -> navController.navigate(route)
            }
        }
        composable(Routes.REGISTRATION) {
            Registration(){
                    route -> navController.navigate(route)
            }
        }
        composable(Routes.MENU) {
            Menu()
        }
    }
}