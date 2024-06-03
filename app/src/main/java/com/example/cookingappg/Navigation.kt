package com.example.cookingappg

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cookingappg.pages.Menu
import com.example.cookingappg.pages.Login
import com.example.cookingappg.pages.Registration

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
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