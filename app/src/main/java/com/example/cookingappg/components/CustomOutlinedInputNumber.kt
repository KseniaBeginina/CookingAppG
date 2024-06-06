package com.example.cookingappg.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White

@Composable
fun CustomOutlinedInputNumber (state: MutableState<String>, label: String, suffix: String) {
    var isEditing by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.size(width = 166.dp, height = 56.dp)
            .onFocusChanged { focused: FocusState ->
                if (!focused.isFocused) {
                    isEditing = false
                }
                if (!isEditing && state.value.isEmpty()){
                    state.value = "90"
                }
            },
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
        label = {
            Text(
            text = label,
            color = TextLight,
            fontFamily = FontFamily(Font(R.font.montserratmedium))
            )
        },
        placeholder = {
            Text(
            text = suffix,
            color = TextLight,
            fontFamily = FontFamily(Font(R.font.montserratmedium)),
            fontSize = 12.sp
            )
        },
        shape = RoundedCornerShape(12.dp),
        value = state.value,
        onValueChange = {
            if (it.isEmpty()) {
                state.value = ""
            } else if (it.all { it.isDigit() } && it.toIntOrNull() in 0..90) {
                state.value = it
            }
            isEditing = true
        }
    )
}
//
//@Preview(showBackground = true)
//@Composable
//private fun CheckInp() {
//    CustomOutlinedInputNumber("", "Enter text", "минут")
//}