package com.example.cookingappg.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.Routes
import com.example.cookingappg.components.CustomButton
import com.example.cookingappg.components.CustomOutlinedInputText
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.data.User
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White
import com.example.cookingappg.ui.theme.White60
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileEdit(navigate:(String)->Unit) {

    val database = Firebase.database.reference
    val auth = Firebase.auth
    var currentUser = auth.currentUser

    FirebaseAuth.getInstance().addAuthStateListener { auth ->
        currentUser = auth.currentUser
    }

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

    val userRef = database.child("User").child(currentUser!!.uid)

    LaunchedEffect(key1 = currentUser) {
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val user = dataSnapshot.getValue(User::class.java)
                    userName.value = user?.name ?: ""
                    email.value = user?.email ?: ""
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Обработка ошибок
            }
        })
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
                    onValueChange = { password.value = it }
                )
                CustomOutlinedInputText(
                    state = newPassword,
                    label = "Новый пароль",
                    suffix = newPassword.value,
                    onValueChange = { newPassword.value = it }
                )
            }

            CustomButton(text = "Сохранить изменения") {
                userRef.updateChildren(
                    mapOf(
                        "name" to userName.value,
                        "email" to email.value
                    )
                )

                currentUser!!.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(userName.value)
                        .build()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("UpdateProfile", "Профиль пользователя обновлен.")
                            // Дополнительные действия после успешного обновления профиля
                        } else {
                            Log.w("UpdateProfile", "Ошибка обновления профиля.", task.exception)
                            // Обработайте ошибку обновления профиля
                        }
                    }

                val credential = EmailAuthProvider
                    .getCredential(currentUser!!.email!!, password.value)

                if (newPassword.value.isNotEmpty()){
                    currentUser!!.reauthenticate(credential)
                        .addOnCompleteListener {task->
                            if (task.isSuccessful){
                                currentUser!!.updatePassword(newPassword.value)
                                    .addOnCompleteListener {passTask->
                                        if (passTask.isSuccessful) {
                                            Log.d("UpdatePassword", "Пароль пользователя обновлен.")
                                        } else {
                                            Log.w("UpdatePassword", "Ошибка обновления пароля.", passTask.exception)
                                        }
                                    }
                            } else{
                                Log.w("Reauthenticate", "Ошибка reauthenticate.", task.exception)
                            }
                        }
                }

//                if (email.value.isNotEmpty() && email.value != currentUser!!.email) {
//                    currentUser!!.reauthenticate(credential)
//                        .addOnCompleteListener{
//                                task->
//                            if (task.isSuccessful){
//                                currentUser!!.updateEmail(email.value)
//                                    .addOnCompleteListener {emailTask->
//                                        if (emailTask.isSuccessful) {
//                                            Log.d("UpdateEmail", "Email пользователя обновлен.")
//                                            currentUser = auth.currentUser
//                                        } else {
//                                            Log.w("UpdateEmail", "Ошибка обновления email.", emailTask.exception)
//                                        }
//                                    }
//                            }
//                        }
//
//                }


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
    MaterialTheme {
        ProfileEdit(){}
    }
}