package com.example.cookingappg.presentation.pages.recipes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cookingappg.R
import com.example.cookingappg.data.Product
import com.example.cookingappg.data.Recipe
import com.example.cookingappg.navigation.Routes
import com.example.cookingappg.presentation.components.CustomButton
import com.example.cookingappg.presentation.components.CustomOutlinedInputText
import com.example.cookingappg.presentation.components.CustomTitle
import com.example.cookingappg.presentation.components.DishTypeChoise
import com.example.cookingappg.ui.theme.Green
import com.example.cookingappg.ui.theme.Primary
import com.example.cookingappg.ui.theme.Red
import com.example.cookingappg.ui.theme.TextDark
import com.example.cookingappg.ui.theme.White
import com.example.cookingappg.ui.theme.White60

@SuppressLint("UnrememberedMutableState", "MutableCollectionMutableState",
    "StateFlowValueCalledInComposition"
)
@Composable
fun EditRecipe(recipe: Recipe, recipeVM: RecipeViewModel, navController: NavController) {

    val recipeId = navController.previousBackStackEntry?.savedStateHandle?.get<Long>("recipeId")

    val name = remember { mutableStateOf(recipe.name) }
    val category = remember { mutableStateOf(recipe.category) }
    val cookTime = remember { mutableStateOf(recipe.cookTime.toString()) }
    val portions = remember { mutableStateOf(recipe.portions.toString()) }
    val calories = remember { mutableStateOf(recipe.calories.toString()) }
    val proteins = remember { mutableStateOf(recipe.proteins.toString()) }
    val fats = remember { mutableStateOf(recipe.fats.toString()) }
    val carbos = remember { mutableStateOf(recipe.carbos.toString()) }
    val recipeContent = remember { mutableStateOf(recipe.recipeContent) }
    val liked = remember { mutableStateOf(recipe.liked) }
    val products = remember { recipe.products.toMutableStateList() }

    val bitmap = recipeVM.bitmap.value
    Log.d("bitmap", bitmap.toString())

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(730.dp)
            .padding(bottom = 72.dp)
            .background(White)
            .verticalScroll(
                rememberScrollState()
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ){
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        navController.navigateUp()
                    }
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        tint = TextDark,
                        contentDescription = null,
                    )
                }
                CustomTitle("Изменить рецепт")
            }

            Column (
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                //Изображение
//                Text(
//                    text = "Добавить фотографию",
//                    fontSize = 16.sp,
//                    fontFamily = FontFamily(Font(R.font.montserratmedium)),
//                    color = TextLight
//                )
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clickable {
                            onClickEditImage(context)
                            navController.currentBackStackEntry?.savedStateHandle?.set("recipeId", recipeId)
                            navController.navigate(Routes.CAMERAREC)
                            Log.d("EditImg", "tap")
                        }
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        model = ImageRequest.Builder(context).data(recipe.image).build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        error = painterResource(id = R.drawable.humster)
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

                //Название
                CustomOutlinedInputText(
                    state = name,
                    label = "Название блюда",
                    suffix = name.value,
                    width = 340.dp,
                    height = 50.dp,
                    single = true,
                    keyboardType = KeyboardType.Text,
                    onValueChange = {
                        name.value = it
                    }
                )
                //Категория

                val categories = listOf(
                    "завтрак", "супы", "горячее", "гарниры", "закуска", "выпечка", "заморозка"
                )

                val selectedStates = remember {
                    mutableListOf(
                        mutableStateOf(true),
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .horizontalScroll(ScrollState(0)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    categories.forEachIndexed {index, cat ->
                        DishTypeChoise(
                            text = cat,
                            selected = selectedStates[index]
                        ){
                            for (i in 0 ..< selectedStates.size) {
                                selectedStates[i].value = false
                            }
                            selectedStates[index].value = true
                            it.value = true
                            category.value = cat
                        }
                    }
                }
            }

            //Продукты
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                products.forEachIndexed { index, product ->
                    ProductRow(product = product, index = index, products = products)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = {
                            products.add(Product("", ""))
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "",
                            tint = Green
                        )
                    }
                }
            }

            //CookingTime and Portions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row (
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(id = R.drawable.time),
                        tint = Primary,
                        contentDescription = null,
                    )
                    CustomOutlinedInputText(
                        state = cookTime,
                        label = "",
                        suffix = cookTime.value,
                        width = 135.dp,
                        height = 32.dp,
                        single = true,
                        keyboardType = KeyboardType.Number,
                        onValueChange = {cookTime.value = it}
                    )
                }

                Row (
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(id = R.drawable.portions),
                        tint = Primary,
                        contentDescription = null,
                    )
                    CustomOutlinedInputText(
                        state = portions,
                        label = "",
                        suffix = portions.value,
                        width = 135.dp,
                        height = 32.dp,
                        single = true,
                        keyboardType = KeyboardType.Number,
                        onValueChange = {portions.value = it}
                    )
                }
            }

            //КБЖУ
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                //Калории
                Row (
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "К",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.montserratregular)),
                        color = Primary
                    )
                    CustomOutlinedInputText(
                        state = calories,
                        label = "",
                        suffix = calories.value,
                        width = 56.dp,
                        height = 32.dp,
                        single = true,
                        keyboardType = KeyboardType.Number,
                        onValueChange = {calories.value = it}
                    )
                }

                //Белки
                Row (
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Б",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.montserratregular)),
                        color = Primary
                    )
                    CustomOutlinedInputText(
                        state = proteins,
                        label = "",
                        suffix = proteins.value,
                        width = 56.dp,
                        height = 32.dp,
                        single = true,
                        keyboardType = KeyboardType.Number,
                        onValueChange = {proteins.value = it}
                    )
                }

                //Жиры
                Row (
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Ж",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.montserratregular)),
                        color = Primary
                    )
                    CustomOutlinedInputText(
                        state = fats,
                        label = "",
                        suffix = fats.value,
                        width = 56.dp,
                        height = 32.dp,
                        single = true,
                        keyboardType = KeyboardType.Number,
                        onValueChange = {fats.value = it}
                    )
                }

                //Углеводы
                Row (
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "У",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.montserratregular)),
                        color = Primary
                    )
                    CustomOutlinedInputText(
                        state = carbos,
                        label = "",
                        suffix = carbos.value,
                        width = 56.dp,
                        height = 32.dp,
                        single = true,
                        keyboardType = KeyboardType.Number,
                        onValueChange = {carbos.value = it}
                    )
                }
            }

            //Описание рецепта
            CustomOutlinedInputText(
                state = recipeContent,
                label = "Рецепт приготовления",
                suffix = recipeContent.value,
                width = 340.dp,
                height = 129.dp,
                single = false,
                keyboardType = KeyboardType.Text,
                onValueChange = {recipeContent.value = it}
            )

            CustomButton(text = "Сохранить изменения") {
                try {
                    recipeVM.updateRecipeData(
                        recipeId = recipeId!!,
                        name = name.value,
                        category = category.value,
                        cookTime = cookTime.value.toFloat().toInt(),
                        portions = portions.value.toFloat().toInt(),
                        calories = calories.value.toFloat(),
                        proteins = proteins.value.toFloat(),
                        fats = fats.value.toFloat(),
                        carbos = carbos.value.toFloat(),
                        recipeContent = recipeContent.value,
                        liked = liked.value,
                        products = products
                    )
                    navController.navigateUp()
                } catch (e: Exception){
                    Log.d("EditException", e.message.toString())
                }
            }
        }
    }
}

@Composable
private fun ProductRow(product: Product, index: Int, products: MutableList<Product>) {
    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Log.d("test", "Product after render: $product")
        val productName = remember(key1 = product) { mutableStateOf(product.name) }
        val productNumber = remember(key1 = product) { mutableStateOf(product.productNumber) }

        // Поле для имени продукта
        CustomOutlinedInputText(
            state = productName,
            label = "Продукт",
            suffix = "",
            width = 186.dp,
            height = 50.dp,
            single = true,
            keyboardType = KeyboardType.Text,
            onValueChange = {
                productName.value = it
                product.name = it
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Поле для количества грамм
        CustomOutlinedInputText(
            state = productNumber,
            label = "Грамм",
            suffix = "гр",
            width = 120.dp,
            height = 50.dp,
            single = true,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                productNumber.value = it
                product.productNumber = it
            }
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Кнопка удаления
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = {
                val removed = products.removeAt(index)
                Log.d("test", removed.toString())
                Log.d("test", productName.value)
                Log.d("test", productNumber.value)
                Log.d("test", product.toString())
                Log.d("test", "products:")
                products.forEach{Log.d("test", it.toString())}
            }
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.deletecircle),
                contentDescription = "",
                tint = Red
            )
        }
    }
}

private fun onClickEditImage(context: Context) {
    if (!hasRequiredPermissions(context)) {
        ActivityCompat.requestPermissions(
            context as Activity, Permissions.CAMERAX_PERMISSIONS, 0
        )
    }
}

private fun hasRequiredPermissions(context: Context): Boolean {
    return Permissions.CAMERAX_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            context,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}
