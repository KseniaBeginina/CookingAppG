package com.example.cookingappg.presentation.pages.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookingappg.R
import com.example.cookingappg.data.RecipePreview
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.presentation.components.CustomTitle
import com.example.cookingappg.presentation.components.DishShortCard
import com.example.cookingappg.presentation.components.DishTypeChoise
import com.example.cookingappg.presentation.components.FilterButton
import com.example.cookingappg.presentation.components.SearchBar
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Home(recipeVM: RecipeViewModel, recipes: List<RecipePreview>, navController: NavController) {

    val text = remember {
        mutableStateOf("")
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
                FilterButton(navController::navigate)
            }

            val categories = listOf(
                "завтрак", "супы", "горячее", "гарниры", "закуска", "выпечка", "заморозка"
            )

            val selectedStates = remember {
                mutableListOf(
                    mutableStateOf(false),
                    mutableStateOf(false),
                    mutableStateOf(false),
                    mutableStateOf(false),
                    mutableStateOf(false),
                    mutableStateOf(false),
                    mutableStateOf(false)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(ScrollState(0)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                categories.forEachIndexed {index, cat ->
                        DishTypeChoise(
                            text = cat,
                            selected = selectedStates[index]
                        ){
                            selectedStates[index].value = !selectedStates[index].value
                        }
                    }
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                if(recipes.isEmpty()){
                    Column (
                        modifier = Modifier.fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Image(
                            modifier = Modifier
                                .size(300.dp),
                            painter = painterResource(id = R.drawable.norec),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "У вас еще нет рецептов. Создайте свой первый рецепт в разделе “Добавить”",
                            color = TextDark,
                            fontFamily = FontFamily(Font(R.font.montserratmedium)),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                } else{
                    FlowRow (
                        modifier = Modifier
                            .fillMaxWidth(0.95F)
                            .wrapContentHeight()
                            .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ){
                    recipes.forEach{recipe ->
                        DishShortCard(recipePrew = recipe, recipeVM = recipeVM) {
                            val recipeDetails = recipeVM.getRecipeDetails(recipe.id)
                            navController.currentBackStackEntry?.savedStateHandle?.set("recipe", recipeDetails)
                            navController.currentBackStackEntry?.savedStateHandle?.set("recipeId", recipe.id)
                            navController.navigate(Routes.RECIPE)
                            }
                        }
                    }
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun HomePrew() {
//    MaterialTheme {
//        Home(){}
//    }
//}