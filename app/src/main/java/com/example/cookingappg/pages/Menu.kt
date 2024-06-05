package com.example.cookingappg.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cookingappg.NavigationItem
import com.example.cookingappg.R
import com.example.cookingappg.Routes
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Menu() {
    val bottomNavigationItems = remember {
        listOf(
            NavigationItem(icon = R.drawable.home, title = "Рецепты"),
            NavigationItem(icon = R.drawable.planer, title = "Планер"),
            NavigationItem(icon = R.drawable.add, title = "Добавить"),
            NavigationItem(icon = R.drawable.kitchen, title = "Кухня"),
            NavigationItem(icon = R.drawable.user, title = "Профиль")
        )
    }
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    selectedItem = remember(key1 = backstackState) {
        when(backstackState?.destination?.route) {
            Routes.HOME -> 0
            Routes.PLANER -> 1
            Routes.ADD -> 2
            Routes.KITCHEN -> 3
            Routes.PROFILE -> 4
            else -> 0
        }
    }

    val showBottomBar = backstackState?.destination?.route != Routes.LOGIN

    Scaffold (
        bottomBar = {
            if (showBottomBar){
                NavBar (bottomNavigationItems, selectedItem) { index ->
                    when (index){
                        0 -> navigateToTab(navController, Routes.HOME)
                        1 -> navigateToTab(navController, Routes.PLANER)
                        2 -> navigateToTab(navController, Routes.ADD)
                        3 -> navigateToTab(navController, Routes.KITCHEN)
                        4 -> navigateToTab(navController, Routes.PROFILE)
                    }
                }
            }
        }
    ){
        NavHost(navController, Routes.HOME){
            composable(Routes.HOME){
                Home(navController::navigate)
            }
            composable(Routes.FILTERS){
                Filters(navController::navigate)
            }
            composable(Routes.LOGIN){
                Login(navController::navigate)
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

@Composable
fun NavBar(
    items: List<NavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = White,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = (index == selected),
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = null
                        )
                        Text(
                            text = item.title,
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.montserratmedium)),
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    selectedTextColor = Primary,
                    unselectedIconColor = TextDark,
                    unselectedTextColor = TextDark,
                    indicatorColor = White
                )
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun CheckMenu() {
    Menu()
}