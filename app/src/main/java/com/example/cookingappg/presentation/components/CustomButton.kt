package com.example.cookingappg.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.White

@Composable
fun CustomButton (text:String, onClick:()->Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = White,
            containerColor = Primary
        ),
        modifier = Modifier.size(width = 300.dp, height = 48.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.montserratsemibold)),
        )
    }
}

@Preview
@Composable
private fun CheckBut() {
    CustomButton("Button") {
        Log.d("0","0")
    }
}