package com.example.cookingappg.pages.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.components.CustomOutlinedInputText
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.ui.theme.Green
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.Red
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White

@Composable
fun Recipe(navigate:(String)->Unit) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .paint(
                    painterResource(id = R.drawable.humster),
                    contentScale = ContentScale.Crop
                )
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center
            ){
                //Назад
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        navigate(Routes.HOME)
                    }
                ){
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.back),
                        tint = TextDark,
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.width(220.dp))
                //Редактировать
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        navigate(Routes.HOME)
                    }
                ){
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(id = R.drawable.edit),
                        tint = TextDark,
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                //Лайк
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        navigate(Routes.HOME)
                    }
                ){
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.like),
                        tint = TextDark,
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                //Больше-удалить
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        navigate(Routes.HOME)
                    }
                ){
                    Icon(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.more),
                        tint = TextDark,
                        contentDescription = null,
                    )
                }
            }
        }
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            //Заголовок
            Column (
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                CustomTitle(text = "Рецепт")
                Text(
                    text = "Завтрак",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
                    color = TextLight
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(TextLight)
                )
            }
            //Параметры
            Column (
                modifier = Modifier.height(60.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(86.dp)
                ) {
                    //Время
                    Row (
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.time),
                            tint = Primary,
                            contentDescription = null,
                        )
                        Text(
                            text = "12 минут",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                            color = TextDark
                        )
                    }
                    //КБЖУ
                    Row (
                        modifier = Modifier
                            .width(203.dp)
                            .height(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        Row (
                            modifier = Modifier.wrapContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "К",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                                color = Primary
                            )
                            Text(
                                text = "125.8",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                                color = TextDark
                            )
                        }
                        Row (
                            modifier = Modifier.wrapContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Б",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                                color = Primary
                            )
                            Text(
                                text = "12",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                                color = TextDark
                            )
                        }
                        Row (
                            modifier = Modifier.wrapContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Ж",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                                color = Primary
                            )
                            Text(
                                text = "6",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                                color = TextDark
                            )
                        }
                        Row (
                            modifier = Modifier.wrapContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "У",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                                color = Primary
                            )
                            Text(
                                text = "25",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                                color = TextDark
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(86.dp)
                ) {
                    //Продукты
                    Row (
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.listhide),
                            tint = Red,
                            contentDescription = null,
                        )
                        Text(
                            text = "Продукты",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                            color = TextDark
                        )
                    }
                    //Порции
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "3",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                            color = TextDark
                        )
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.portions),
                            tint = Primary,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckRecipe() {
    Recipe(){}
}