package com.example.cookingappg.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookingappg.R
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.ui.theme.TextLight

@Composable
fun FilterButton(onClick:()->Unit) {

    IconButton(
        modifier = Modifier.size(42.dp),
        onClick = {
            onClick()
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.filter),
            contentDescription = null,
            tint = TextLight
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckfilterBut() {
    FilterButton(){}
}