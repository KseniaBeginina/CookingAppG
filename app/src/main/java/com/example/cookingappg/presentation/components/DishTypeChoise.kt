package com.example.cookingappg.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White

@Composable
fun DishTypeChoise (text:String, onClick:()->Unit) {
    var containerColor by remember{ mutableStateOf(White) }
    var textColor by remember{ mutableStateOf(TextLight) }
    var borderColor by remember{ mutableStateOf(TextLight) }

    Button(
        modifier = Modifier.wrapContentSize(),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, borderColor),
        onClick = {
            containerColor = Primary
            textColor = White
            borderColor = Primary
        }
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp),
            text = text,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.montserratsemibold)),
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckDishType() {
    DishTypeChoise("завтрак"){}
}