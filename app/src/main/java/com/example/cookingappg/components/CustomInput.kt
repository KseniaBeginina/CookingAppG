package com.example.cookingappg.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White

@Composable
fun CustomInput (text:String, placeholder:String) {
    var newText by remember {
        mutableStateOf(text)
    }

    TextField(
        modifier = Modifier.size(width = 343.dp, height = 56.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = White,
            unfocusedTextColor = TextLight,
            unfocusedIndicatorColor = TextLight,
            focusedContainerColor = White,
            focusedTextColor = TextDark,
            focusedIndicatorColor = Primary
        ),
        textStyle = TextStyle(
            fontSize =  16.sp,
            fontFamily = FontFamily(Font(R.font.montserratmedium)),
        ),
        placeholder = { Text(
            text = placeholder,
            color = TextLight,
            fontFamily = FontFamily(Font(R.font.montserratmedium))
        ) },
        value = newText,
        onValueChange = { newText = it }
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckInp() {
    CustomInput("","Enter text")
}