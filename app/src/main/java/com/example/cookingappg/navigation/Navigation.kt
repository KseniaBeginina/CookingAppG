package com.example.cookingappg.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cookingappg.pages.Menu
import com.example.cookingappg.pages.authentication.AuthViewModel
import com.example.cookingappg.pages.authentication.Login
import com.example.cookingappg.pages.authentication.Registration

@Composable
fun NavGraph(startDestination: String, navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        navigation(
            route = Routes.AUTH,
            startDestination = Routes.LOGIN
        ) {
            composable(Routes.LOGIN) {
                val authVM: AuthViewModel = hiltViewModel()
                Login(authVM){
                        route -> navController.navigate(route)
                }
            }
            composable(Routes.REGISTRATION) {
                val authVM: AuthViewModel = hiltViewModel()
                Registration(authVM){
                        route -> navController.navigate(route)
                }
            }
        }

        navigation(
            route = Routes.MENUDEST,
            startDestination = Routes.MENU
        ) {
            composable(Routes.MENU) {
                val authVM: AuthViewModel = hiltViewModel()
                Menu(authVM)
            }
        }
    }
}