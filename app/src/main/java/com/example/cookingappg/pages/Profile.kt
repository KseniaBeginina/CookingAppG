package com.example.cookingappg.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookingappg.R
import com.example.cookingappg.Routes
import com.example.cookingappg.components.CustomButton
import com.example.cookingappg.components.CustomTitle
import com.example.cookingappg.components.DishTypeChoise
import com.example.cookingappg.components.FilterButton
import com.example.cookingappg.components.SearchBar
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Profile(navigate:(String)->Unit) {

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
                        navigate(Routes.SETTINGS)
                        Log.d("Settings", "tap")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.setting),
                        contentDescription = null,
                        tint = Primary
                    )
                }

                Spacer(
                    modifier = Modifier.width(86.dp)
                )

                CustomTitle("Профиль")

                Spacer(
                    modifier = Modifier.width(42.dp)
                )

                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        navigate(Routes.EDITPROF)
                        Log.d("EditProfile", "tap")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = null,
                        tint = Primary
                    )
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        navigate(Routes.LOGIN)
                        Log.d("LogOut", "User email: ${FirebaseAuth.getInstance().currentUser?.email}")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = null,
                        tint = Primary
                    )
                }
            }

            Image(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.humster),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                CustomTitle(
                    text = ""
                )

//                Text(
//                    text = "No family",
//                    fontSize = 16.sp,
//                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
//                    color = TextLight
//                )

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

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Любимое",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.montserratsemibold)),
                color = TextDark
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth().height(320.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
//                itemsIndexed(dishes) { index, dish ->
//                    if (index % 2 == 0) {
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            horizontalArrangement = Arrangement.spacedBy(12.dp)
//                        ) {
//                            DishShortCard(dish, {}, navigate)
//                            dishes.getOrNull(index + 1)?.let { DishShortCard(it, {}, navigate) }
//                        }
//                    }
                }

//            CustomButton(text = "Семья") {
//                Log.d("Family", "tap")
//            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ProfilePrew() {
    MaterialTheme {
        Profile(){}
    }
}