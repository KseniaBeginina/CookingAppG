package com.example.cookingappg.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cookingappg.R
import com.example.cookingappg.data.Product
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.data.Recipe
import com.example.cookingappg.data.RecipePreview
import com.example.cookingappg.presentation.pages.recipes.RecipeViewModel
import com.example.cookingappg.ui.theme.Gray
import com.example.cookingappg.ui.theme.Red
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight

@Composable
fun DishShortCard(recipePrew: RecipePreview, recipeVM: RecipeViewModel, onClick:()->Unit) {

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .width(164.dp)
            .height(154.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                2.dp, Gray, RoundedCornerShape(12.dp)
            )
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .height(100.dp)
                .width(164.dp),
            model = ImageRequest.Builder(context).data(recipePrew.image).build(),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .height(2.dp)
                .width(164.dp)
                .background(TextDark)
        )
        Row (
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier.width(112.dp),
                text = recipePrew.name,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserratmedium)),
                color = TextDark
            )
            var liked by remember {
                mutableStateOf(recipePrew.liked)
            }
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        Log.d("Like", "tap")
                        recipeVM.toggleLike(recipePrew.id)
                        liked = !liked
                    },
                painter = painterResource(id = R.drawable.heart),
                tint = if (liked) Red else TextLight,
                contentDescription = null
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun CheckDishShortCard() {
//    val recipe = Recipe(
//        userUid = "1",
//        name = "test",
//        category = "test",
//        img = "",
//        cookTime = 15,
//        portions = 2,
//        calories = 150f,
//        proteins = 10f,
//        fats = 3.5f,
//        carbos = 16f,
//        recipeContent = "test rec",
//        liked = true,
//        products = List<Product>()
//    )
//    DishShortCard(recipe,{}){}
//}