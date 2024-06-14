package com.example.cookingappg.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.cookingappg.presentation.pages.Menu
import com.example.cookingappg.presentation.pages.authentication.AuthViewModel
import com.example.cookingappg.presentation.pages.authentication.Login
import com.example.cookingappg.presentation.pages.authentication.Registration
import com.example.cookingappg.presentation.pages.user.ProfileViewModel

@Composable
fun NavGraph(startDestination: String){
    val navController = rememberNavController()

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
                Menu(navController)
            }
        }
    }
}