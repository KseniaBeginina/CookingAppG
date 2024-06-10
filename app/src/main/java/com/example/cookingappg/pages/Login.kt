package com.example.cookingappg.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.Routes
import com.example.cookingappg.components.CustomButton
import com.example.cookingappg.components.CustomInput
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.White
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.annotation.Nonnull

@Composable
fun Login(navigate:(String)->Unit) {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val auth = Firebase.auth

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cook2),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
            CustomTitle("Войти")
            CustomInput(email, "Email")
            CustomInput(password, "Пароль")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ){
            CustomButton(text = "Войти"){
                Log.d("Login","click")
                if (email.value.isEmpty() || password.value.isEmpty()){
                    Toast.makeText(context, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
                }else if(password.value.length<6){
                    Toast.makeText(context, "Пароль должен быть не менее 6 символов", Toast.LENGTH_SHORT).show()
                }else{
                    auth.signInWithEmailAndPassword(email.value, password.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navigate(Routes.MENU)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Такого аккаунта не существует",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "У меня еще нет аккаунта, хочу ",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
                    color = TextDark
                )
                Text(
                    text = "зарегистрироваться",
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
                    color = Primary,
                    modifier = Modifier.clickable {
                        navigate(Routes.REGISTRATION)
                    }
                )
            }
        }



    }
}

@Preview(showBackground = true)
@Composable
fun LogPrew() {
    MaterialTheme {
        Login(){}
    }
}