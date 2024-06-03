package com.example.cookingappg.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
fun CustomOutlinedInput (text: String, label: String, suffix: String) {
    var newText by remember {
        mutableStateOf(text)
    }

    OutlinedTextField(
        modifier = Modifier.size(width = 166.dp, height = 56.dp),
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
        label = { Text(
            text = label,
            color = TextLight,
            fontFamily = FontFamily(Font(R.font.montserratmedium))
        ) },
        placeholder = { Text(
            text = suffix,
            color = TextLight,
            fontFamily = FontFamily(Font(R.font.montserratmedium))
        ) },
        shape = RoundedCornerShape(12.dp),
        value = newText,
        onValueChange = { newText = it }
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckInp() {
    CustomOutlinedInput("", "Enter text", "минут")
}