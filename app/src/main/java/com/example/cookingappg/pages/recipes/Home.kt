package com.example.cookingappg.pages.recipes

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.components.DishShortCard
import com.example.cookingappg.components.DishTypeChoise
import com.example.cookingappg.components.FilterButton
import com.example.cookingappg.components.SearchBar
import com.example.cookingappg.data.Product
import com.example.cookingappg.data.Recipe
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.ui.theme.White

@Composable
fun Home(navigate:(String)->Unit) {

    val text = remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier.fillMaxSize().background(White)
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

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
//                itemsIndexed(dishes) { index, dish ->
//                    if (index % 2 == 0) {
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.spacedBy(12.dp)
//                        ) {
//                            DishShortCard(dish, {}, navigate)
//                            dishes.getOrNull(index + 1)?.let { DishShortCard(it, {}, navigate) }
//                        }
//                    }
//                }
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