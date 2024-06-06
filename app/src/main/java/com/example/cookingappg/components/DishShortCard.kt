package com.example.cookingappg.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.DishCard
import com.example.cookingappg.R
import com.example.cookingappg.Routes
import com.example.cookingappg.data.Recipe
import com.example.cookingappg.ui.theme.Gray
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight

@Composable
fun DishShortCard(recipe: Recipe, onClick:()->Unit, navigate:(String)->Unit) {
    Column(
        modifier = Modifier
            .width(164.dp)
            .height(154.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                2.dp, Gray, RoundedCornerShape(12.dp)
            )
            .clickable {
                navigate(Routes.RECIPE)
            }
    ) {
        Image(
            modifier = Modifier
                .height(100.dp)
                .width(164.dp),
            painter = painterResource(id = R.drawable.humster),
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
                text = recipe.name,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserratmedium)),
                color = TextDark
            )
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = R.drawable.heart),
                tint = TextLight,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckDishShortCard() {
    val recipe = Recipe(
        userId = 1,
        name = "test",
        category = "test",
        img = "",
        cookTime = 15,
        portions = 2,
        calories = 150f,
        proteins = 10f,
        fats = 3.5f,
        carbos = 16f,
        rec = "test rec",
        liked = true
    )
    DishShortCard(recipe,{}){}
}