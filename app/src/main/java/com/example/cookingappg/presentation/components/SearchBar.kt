package com.example.cookingappg.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
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

@Composable
fun SearchBar (state: MutableState<String>, onChange:(String) -> Unit) {

    var iconTint by remember {
        mutableStateOf(TextLight)
    }

    BasicTextField(
        value = state.value,
        onValueChange = { onChange(it) },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.montserratmedium)),
            color = TextDark
        ),
        singleLine = true,
        cursorBrush = SolidColor(TextDark),
        modifier = Modifier.width(284.dp).height(42.dp)
            .background(Color.White)
            .border(2.dp, TextLight, RoundedCornerShape(24.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        decorationBox = {innerTextField ->
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    tint = iconTint
                )
                Spacer(modifier = Modifier.width(8.dp))

                if (state.value.isEmpty()) {
                    Text(
                        text = "Введите блюдо/продукт",
                        color = TextLight
                    )
                }
                else {
                    innerTextField()
                    iconTint = Primary
                }
            }
        }
    )

}
//
//@Preview(showBackground = true)
//@Composable
//private fun CheckSearchBar() {
//    SearchBar("")
//}