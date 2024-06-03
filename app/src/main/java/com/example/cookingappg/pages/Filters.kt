package com.example.cookingappg.pages

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.Routes
import com.example.cookingappg.components.CustomOutlinedInput
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.components.TwoColorButton
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.Simple
import com.example.cookingappg.ui.theme.TextDark

@Composable
fun Filters(navigate:(String)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = {
                    navigate(Routes.HOME)
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
            TwoColorButton("Избранное") {}
            TwoColorButton("Мои рецепты") {}
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

            var sliderPosition by remember{
                mutableStateOf(0f..90f)
            }
            var from by remember {
                mutableStateOf("")
            }
            var to by remember {
                mutableStateOf("")
            }

            RangeSlider(
                value = sliderPosition,
                valueRange = 0f..90f,
                steps = 16,
                onValueChange = {
                    sliderPosition = it
                    from = sliderPosition.toString()
                    Log.d("Slider","$sliderPosition")
                },
                onValueChangeFinished = {
                    to = sliderPosition.toString()
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
                CustomOutlinedInput(from, "От ", "минут")
                CustomOutlinedInput(to, "До ", "минут")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckFilters() {
    Filters (){}
}