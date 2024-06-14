package com.example.cookingappg.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.White

@Composable
fun MenuDolb(navigate:(String)->Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(horizontal = 14.dp)
            .background(White, RoundedCornerShape(12.dp, 12.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        But("Рецепты", R.drawable.home){
            Log.d("Menu","home")
            navigate(Routes.HOME)
        }
        But("Планер", R.drawable.planer){
            Log.d("Menu","planer")
            navigate(Routes.HOME)
        }
        But("Добавить", R.drawable.add){
            Log.d("Menu","add")
            navigate(Routes.HOME)
        }
        But("Кухня", R.drawable.kitchen){
            Log.d("Menu","kitchen")
            navigate(Routes.HOME)
        }
        But("Профиль", R.drawable.user){
            Log.d("Menu","profile")
            navigate(Routes.LOGIN)
        }
    }
}

@Composable
private fun But(text:String, icon:Int, onClick:()->Unit ){
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = icon),
            tint = TextDark,
            contentDescription = null
        )
        Text(
            text = text,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.montserratmedium)),
            color = TextDark
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun CheckMenuDolb() {
    MenuDolb(){}
}