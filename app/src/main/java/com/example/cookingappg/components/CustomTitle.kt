package com.example.cookingappg.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.ui.theme.TextDark

@Composable
fun CustomTitle (text:String) {
    Text(
        modifier = Modifier.wrapContentWidth(),
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.montserratsemibold)),
        color = TextDark
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckTit() {
    CustomTitle(text = "Title")
}