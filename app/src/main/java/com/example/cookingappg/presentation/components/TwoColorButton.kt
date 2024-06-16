package com.example.cookingappg.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.cookingappg.ui.theme.Simple
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.White

@Composable
fun TwoColorButton (text:String, selected: MutableState<Boolean>, onClick:(MutableState<Boolean>)->Unit) {
    var containerColor by remember(key1 = selected.value){ mutableStateOf(if (selected.value) Primary else Simple) }
    var textColor by remember(key1 = selected.value){ mutableStateOf(if (selected.value) White else TextDark) }

    Button(
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = containerColor
        ),
        modifier = Modifier
            .width(166.dp)
            .height(44.dp),
        onClick = {
            onClick(selected)
            if (selected.value){
                containerColor = Primary
                textColor = White
            } else{
                containerColor = Simple
                textColor = TextDark
            }

        }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.montserratsemibold)),
        )
    }
}

//@Preview
//@Composable
//private fun CheckBut() {
//    TwoColorButton("Button") {
//        Log.d("0","0")
//    }
//}