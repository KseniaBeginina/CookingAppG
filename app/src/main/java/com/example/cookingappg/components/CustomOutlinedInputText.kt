package com.example.cookingappg.components

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults.ContainerBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CustomOutlinedInputText(
    state: MutableState<String>,
    label: String? = null,
    suffix: String,
    width: Dp,
    height: Dp,
    single: Boolean,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
){
    var lab: @Composable (() -> Unit)? = null
    if (!label.isNullOrBlank()) {
        lab = {
                Text(
                    text = label,
                    color = TextLight,
                    fontFamily = FontFamily(Font(R.font.montserratmedium))
                )
        }
    }
    val colors = TextFieldDefaults.colors(
        unfocusedContainerColor = White,
        unfocusedTextColor = TextLight,
        unfocusedIndicatorColor = TextLight,
        focusedContainerColor = White,
        focusedTextColor = TextDark,
        focusedIndicatorColor = Primary
    )

    BasicTextField(
        value = state.value,
        onValueChange = onValueChange,
        modifier = Modifier
            .heightIn(min = 32.dp)
            .height(height)
            .width(width),
        textStyle = TextStyle(
            fontSize =  16.sp,
            fontFamily = FontFamily(Font(R.font.montserratmedium)),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = single,
        decorationBox = {
            OutlinedTextFieldDefaults.DecorationBox(
                value = state.value,
                colors = colors,
                placeholder = {
                    Text(
                        text = suffix,
                        color = TextLight,
                        fontFamily = FontFamily(Font(R.font.montserratmedium)),
                        fontSize = 12.sp
                    )
                },
                container = {
                    ContainerBox(
                        colors = colors,
                        enabled = true,
                        interactionSource = MutableInteractionSource(),
                        isError = false,
                        shape = RoundedCornerShape(12.dp)
                    )
                },
                innerTextField = it,
                label = lab,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                singleLine = single,
                interactionSource = MutableInteractionSource(),
                contentPadding = PaddingValues(start = 8.dp, top = 0.dp)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CustomOutlinedInputText(
    state: String,
    label: String? = null,
    suffix: String,
    width: Dp,
    height: Dp,
    single: Boolean,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
){
    var lab: @Composable (() -> Unit)? = null
    if (!label.isNullOrBlank()) {
        lab = {
            Text(
                text = label,
                color = TextLight,
                fontFamily = FontFamily(Font(R.font.montserratmedium))
            )
        }
    }
    val colors = TextFieldDefaults.colors(
        unfocusedContainerColor = White,
        unfocusedTextColor = TextLight,
        unfocusedIndicatorColor = TextLight,
        focusedContainerColor = White,
        focusedTextColor = TextDark,
        focusedIndicatorColor = Primary
    )

    BasicTextField(
        value = state,
        onValueChange = onValueChange,
        modifier = Modifier
            .heightIn(min = 32.dp)
            .height(height)
            .width(width),
        textStyle = TextStyle(
            fontSize =  16.sp,
            fontFamily = FontFamily(Font(R.font.montserratmedium)),
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = single,
        decorationBox = {
            OutlinedTextFieldDefaults.DecorationBox(
                value = state,
                colors = colors,
                placeholder = {
                    Text(
                        text = suffix,
                        color = TextLight,
                        fontFamily = FontFamily(Font(R.font.montserratmedium)),
                        fontSize = 12.sp
                    )
                },
                container = {
                    ContainerBox(
                        colors = colors,
                        enabled = true,
                        interactionSource = MutableInteractionSource(),
                        isError = false,
                        shape = RoundedCornerShape(12.dp)
                    )
                },
                innerTextField = it,
                label = lab,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                singleLine = single,
                interactionSource = MutableInteractionSource(),
                contentPadding = PaddingValues(start = 8.dp, top = 0.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckInp() {
    Column(
        modifier = Modifier.size(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        CustomOutlinedInputText(
            state = remember { mutableStateOf("State") },
            suffix = "suffix",
            width = 135.dp,
            height = 32.dp,
            single = true,
            keyboardType = KeyboardType.Text
        ) {

        }
    }

}