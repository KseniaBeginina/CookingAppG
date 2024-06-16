package com.example.cookingappg.presentation.pages.recipes

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookingappg.R
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.presentation.components.CustomOutlinedInputNumber
import com.example.cookingappg.presentation.components.CustomTitle
import com.example.cookingappg.presentation.components.TwoColorButton
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.Simple
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.White

@SuppressLint("UnrememberedMutableState")
@Composable
fun Filters(navController: NavController) {

    val likedGet = navController.previousBackStackEntry?.savedStateHandle?.get<Boolean>("liked")
    val fromGet = navController.previousBackStackEntry?.savedStateHandle?.get<String>("from")
    val toGet = navController.previousBackStackEntry?.savedStateHandle?.get<String>("to")

    val liked = remember {
        mutableStateOf(likedGet ?: false)
    }

    val sliderPosition = remember{
        mutableStateOf(0f..90f)
    }
    val from = remember {
        mutableStateOf(fromGet ?: "0")
    }
    val to = remember {
        mutableStateOf(toGet?: "90")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(86.dp)
        ){
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("liked", liked.value)
                    navController.currentBackStackEntry?.savedStateHandle?.set("from", from.value)
                    navController.currentBackStackEntry?.savedStateHandle?.set("to", to.value)
                    navController.navigate(Routes.HOME)
                }
            ){
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    tint = TextDark,
                    contentDescription = null,
                )
            }
            CustomTitle("Фильтры")
        }

        Text(
            text = "Категории",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.montserratsemibold)),
            color = TextDark
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TwoColorButton(
                text = "Избранное",
                selected = liked
            ) {
                liked.value = !liked.value
            }
//            TwoColorButton("Мои рецепты") {}
        }

        Column (
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ){
            Text(
                text = "Время",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                color = TextDark
            )

            LaunchedEffect(key1 = from.value, key2 = to.value) {
                if (from.value.isNotEmpty() && to.value.isNotEmpty()) {
                    sliderPosition.value = from.value.toFloat()..to.value.toFloat()
                }
            }

            RangeSlider(
                value = sliderPosition.value,
                valueRange = 0f..90f,
                steps = 90,
                onValueChange = {
                    sliderPosition.value = it
                    from.value = sliderPosition.value.start.toInt().toString()
                    to.value = sliderPosition.value.endInclusive.toInt().toString()
                    Log.d("Slider","$sliderPosition")
                },
                colors = SliderDefaults.colors(
                    thumbColor = Primary,
                    activeTrackColor = Primary,
                    activeTickColor = Primary,
                    inactiveTrackColor = Simple,
                    inactiveTickColor = Simple,
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CustomOutlinedInputNumber(from, "От ", "минут")
                CustomOutlinedInputNumber(to, "До ", "минут")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun CheckFilters() {
//    Filters (){}
//}