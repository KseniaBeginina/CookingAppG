package com.example.cookingappg

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cookingappg.pages.Menu
import com.example.cookingappg.pages.Login
import com.example.cookingappg.pages.Registration
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(navController: NavHostController){
    val startDestination = if (FirebaseAuth.getInstance().currentUser == null) Routes.LOGIN else Routes.MENU
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