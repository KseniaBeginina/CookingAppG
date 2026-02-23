package com.example.cookingappg.presentation.pages.user

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cookingappg.R
import com.example.cookingappg.data.RecipePreview
import com.example.cookingappg.presentation.components.CustomTitle
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.presentation.components.DishShortCard
import com.example.cookingappg.presentation.pages.recipes.RecipeViewModel
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Profile(previews: List<RecipePreview>, recipeVM: RecipeViewModel, profileVM: ProfileViewModel, mainNavController: NavController, navController: NavController) {

    val userData = profileVM.getUserData()
    val userName = userData.name
    val image = userData.image

    val previewsState = remember {
        mutableStateOf(previews)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        mainNavController.navigate(Routes.SETTINGS)
                        Log.d("Settings", "tap")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.setting),
                        contentDescription = null,
                        tint = Primary
                    )
                }
                Spacer(
                    modifier = Modifier.width(86.dp)
                )
                CustomTitle("Профиль")
                Spacer(
                    modifier = Modifier.width(42.dp)
                )
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        navController.navigate(Routes.EDITPROF)
                        Log.d("EditProfile", "tap")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = null,
                        tint = Primary
                    )
                }
                Spacer(
                    modifier = Modifier.width(12.dp)
                )
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {

                        mainNavController.navigate(Routes.AUTH)
                        profileVM.logOut()
//                            navController.popBackStack(Routes.MENUDEST, true)
//                            navController.navigate(Routes.AUTH)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = null,
                        tint = Primary
                    )
                }
            }

            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(context).data(image).build(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CustomTitle(
                    text = userName
                )

//                Text(
//                    text = "No family",
//                    fontSize = 16.sp,
//                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
//                    color = TextLight
//                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(TextLight)
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Любимое",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                color = TextDark
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(0.95F)
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    previewsState.value.forEach { preview ->
                        DishShortCard(recipePrew = preview, recipeVM = recipeVM, onLikeClick = {
                            if (!it) {
                                previewsState.value = recipeVM.getWithFilters(
                                    query = "",
                                    categories = listOf(),
                                    liked = true,
                                    minTime = 0,
                                    maxTime = 90
                                )
                            }
                        }) {
                            val recipeDetails = recipeVM.getRecipeDetails(preview.id)
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "recipe",
                                recipeDetails
                            )
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "recipeId",
                                preview.id
                            )
                            navController.navigate(Routes.RECIPE)
                        }
                    }
                }
            }

//            CustomButton(text = "Семья") {
//                Log.d("Family", "tap")
//            }
        }
    }
}


//
//@Preview(showBackground = true)
//@Composable
//fun ProfilePrew() {
//    MaterialTheme {
//        Profile(){}
//    }
//}