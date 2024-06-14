package com.example.cookingappg.presentation.pages.recipes

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.presentation.components.CustomTitle
import com.example.cookingappg.presentation.components.DishShortCard
import com.example.cookingappg.presentation.components.DishTypeChoise
import com.example.cookingappg.presentation.components.FilterButton
import com.example.cookingappg.presentation.components.SearchBar
import com.example.cookingappg.data.Product
import com.example.cookingappg.data.Recipe
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White

@Composable
fun Home(navigate:(String)->Unit) {

    val text = remember {
        mutableStateOf("")
    }
    val recipes = remember {
        mutableStateListOf<Recipe>()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            CustomTitle("Рецепты")

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SearchBar(state = text)
                FilterButton(navigate)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(ScrollState(0)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DishTypeChoise(text = "завтрак") {}
                DishTypeChoise(text = "обед") {}
                DishTypeChoise(text = "ужин") {}
                DishTypeChoise(text = "бебра") {}
            }
            val recipe = Recipe(
                userUid = "1",
                name = "test",
                category = "test",
                img = "",
                cookTime = 15,
                portions = 2,
                calories = 150f,
                proteins = 10f,
                fats = 3.5f,
                carbos = 16f,
                recipeContent = "test rec",
                liked = true
            )
            DishShortCard(recipe = recipe){navigate(Routes.RECIPE)}

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                recipes.forEach{recipe ->
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        DishShortCard(recipe = recipe) {
                            /*Перейти на страницу рецепта*/
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomePrew() {
    MaterialTheme {
        Home(){}
    }
}