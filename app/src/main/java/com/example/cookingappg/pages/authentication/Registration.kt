package com.example.cookingappg.pages.authentication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.components.CustomButton
import com.example.cookingappg.components.CustomCheckBox
import com.example.cookingappg.components.CustomInput
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White
import retrofit2.HttpException

@Composable
fun Registration(authVM: AuthViewModel, navigate:(String)->Unit) {
    val email = remember {
        mutableStateOf("")
    }
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val check = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cook),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            CustomTitle("Создать аккаунт")
            CustomInput(email, "Email", KeyboardType.Email)
            CustomInput(username, "Имя", KeyboardType.Text)
            CustomInput(password, "Пароль", KeyboardType.Password)

            Row(
                modifier = Modifier.width(343.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomCheckBox(check)
                Text(
                    text = "Я согласен с ",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
                    color = TextLight
                )
                Text(
                    text = "вами",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
                    color = Primary,
                    modifier = Modifier.clickable {
                        Log.d("Privacy","click")
                    }
                )
            }

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.width(146.dp)
                )
                Text(
                    text = "ИЛИ",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
                    color = TextLight
                )
                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier.width(146.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.socials),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        Log.d("Google", "click")
                    }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ){
            CustomButton(text = "Создать аккаунт"){
                Log.d("Registration","click")
                if (email.value.isEmpty() || password.value.isEmpty() || username.value.isEmpty()){
                    Toast.makeText(context, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
                }else if(password.value.length<6){
                    Toast.makeText(context, "Пароль должен быть не менее 6 символов", Toast.LENGTH_SHORT).show()
                }else if (!check.value){
                    Toast.makeText(context, "Необходимо согласиться", Toast.LENGTH_SHORT).show()
                }else{
                    try {
                        authVM.signUp(email.value, username.value, password.value)
                        navigate(Routes.MENU)
                    }catch (e: HttpException){
                        if (e.code() == 400){
                            Toast.makeText(
                                context,
                                "Проверьте введенные данные",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Повторите попытку",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }catch (e: Exception){
                        Log.d("test", e.toString())
                        Toast.makeText(
                            context,
                            "Попробуйте еще раз",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
            Row (
            ){
                Text(
                    text = "У меня уже есть аккаунт, хочу ",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
                    color = TextDark
                )
                Text(
                    text = "войти",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
                    color = Primary,
                    modifier = Modifier.clickable {
                        navigate(Routes.LOGIN)
                    }
                )
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun RegPrew() {
//    MaterialTheme {
//        Registration(){}
//    }
//}