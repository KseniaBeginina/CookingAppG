package com.example.cookingappg.pages.user

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.components.CustomButton
import com.example.cookingappg.components.CustomOutlinedInputText
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White
import com.example.cookingappg.ui.theme.White60

@Composable
fun ProfileEdit(navigate:(String)->Unit) {

    val userName = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val newPassword = remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier.fillMaxSize().background(White)
    ){
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        navigate(Routes.PROFILE)
                        Log.d("Back", "tap")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        tint = TextDark
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 86.dp)
                ){
                    CustomTitle("Профиль")
                }

            }

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clickable {
                        Log.d("EditImg", "tap")
                    }
            ) {
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.humster),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(White60)
                        .clip(CircleShape)
                ){
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            modifier = Modifier.padding(top = 35.dp),
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = null,
                            tint = TextDark
                        )
                        Text(
                            text = "Изменить",
                            color = TextDark,
                            fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Column (
                modifier = Modifier.height(424.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                CustomOutlinedInputText(
                    state = userName,
                    label = "Ваше имя",
                    suffix = userName.value,
                    width = 340.dp,
                    height = 50.dp,
                    single = true,
                    keyboardType = KeyboardType.Text,
                    onValueChange = { userName.value = it }
                )
//                CustomOutlinedInputText(
//                    state = email,
//                    label = "Email",
//                    suffix = email.value,
//                    onValueChange = { email.value = it }
//                )
                CustomOutlinedInputText(
                    state = password,
                    label = "Старый пароль",
                    suffix = password.value,
                    width = 340.dp,
                    height = 50.dp,
                    single = true,
                    keyboardType = KeyboardType.Password,
                    onValueChange = { password.value = it }
                )
                CustomOutlinedInputText(
                    state = newPassword,
                    label = "Новый пароль",
                    suffix = newPassword.value,
                    width = 340.dp,
                    height = 50.dp,
                    single = true,
                    keyboardType = KeyboardType.Password,
                    onValueChange = { newPassword.value = it }
                )
            }

            CustomButton(text = "Сохранить изменения") {
                Log.d("Save", "tap")
            }

            Text(
                text = "Удалить аккаунт",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                color = TextLight,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    Log.d("Delete", "tap")
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ProfileEditPrew() {
    ProfileEdit(){}
}