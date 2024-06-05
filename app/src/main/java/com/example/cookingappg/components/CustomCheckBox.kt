package com.example.cookingappg.components

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.TextLight
import com.example.cookingappg.ui.theme.White

@Composable
fun CustomCheckBox(state: MutableState<Boolean>) {

    Checkbox(
        modifier = Modifier.height(20.dp).width(32.dp).padding(end = 12.dp),
        checked = state.value,
        onCheckedChange = {
            state.value = it
            Log.d("Check value", state.value.toString())
        },
        colors = CheckboxDefaults.colors(
            checkedColor = Primary,
            checkmarkColor = White,
            uncheckedColor = TextLight
        )
    )
}
//
//@Preview(showBackground = true)
//@Composable
//private fun CheckCB() {
//    CustomCheckBox(false)
//}