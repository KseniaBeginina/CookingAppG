package com.example.cookingappg.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.cookingappg.components.CustomCheckBox
import com.example.cookingappg.components.CustomInput
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Registration(navigate:(String)->Unit) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
            CustomInput(email, "Email")
            CustomInput(username, "Имя")
            CustomInput(password, "Пароль")

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
                modifier = Modifier.size(32.dp).clickable {
                    Log.d("Google","click")
                }
            )
        }

        val context = LocalContext.current

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
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.value, password.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userInfo: HashMap<String, String> = HashMap()
                                userInfo.put("email", email.value)
                                userInfo.put("name", username.value)
                                FirebaseDatabase.getInstance().getReference()
                                    .child("Users")
                                    .child(FirebaseAuth.getInstance().currentUser!!.uid)
                                    .setValue(userInfo)
                                navigate(Routes.MENU)
                            } else {
                                Toast.makeText(context, "Ошибка регистрации", Toast.LENGTH_SHORT)
                                    .show()
                            }
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

@Preview(showBackground = true)
@Composable
fun RegPrew() {
    MaterialTheme {
        Registration(){}
    }
}