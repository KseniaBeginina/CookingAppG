package com.example.cookingappg.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White

@Composable
fun CustomAlert (text:String, actionText: String, dismissClick:()->Unit, confirmClick:()->Unit) {
    AlertDialog(
        modifier = Modifier
            .wrapContentHeight()
            .width(350.dp),
        containerColor = White,
        text = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = text,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                color = TextDark,
            )
        },
        onDismissRequest = { /**/ },
        confirmButton = {
            CustomButton(text = actionText) {
                confirmClick()
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = TextLight,
                    containerColor = White
                ),
                onClick = { dismissClick() }
            ) {
                Text(
                    text = "Отмена",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium))
                )
            }
        },
    )
}

//@Preview
//@Composable
//private fun CheckAlert() {
//    CustomAlert("Вы точно хотите удалить рецепт?", "Удалить")
//}