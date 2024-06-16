package com.example.cookingappg.presentation.pages.recipes

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingappg.data.Product
import com.example.cookingappg.data.Recipe
import com.example.cookingappg.data.api.RecipeApi
import com.example.cookingappg.data.api.dto.AddRecipeRequest
import com.example.cookingappg.data.local_manager.LocalManager
import com.example.cookingappg.data.RecipePreview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Query
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeApi: RecipeApi,
    private val prefs: SharedPreferences,
    private val localManager: LocalManager
): ViewModel(){

    private val _bitmap = MutableStateFlow<Bitmap?>(null)
    val bitmap = _bitmap.asStateFlow()

    fun onTakePhoto(context: Context, bitmap: Bitmap?) {
        Log.d("test", "bitmap: $bitmap")
        _bitmap.value = bitmap
    }

    fun onTakePhotoUpdate(recipeId: Long?, context: Context, bitmap: Bitmap?) {
        Log.d("test", "bitmap: $bitmap")
        _bitmap.value = bitmap
        if (bitmap != null && recipeId != null) {
            updateRecipeImage(recipeId, bitmap, context)
        }
    }

    fun getRecipePreview():List<RecipePreview>{
        val userId = prefs.getLong("userId", 0)
        var recipePrew: List<RecipePreview>
        runBlocking {
            recipePrew = recipeApi.getRecipePreview(userId)
        }
        return recipePrew
    }

    fun getRecipeDetails(id: Long):Recipe{
        var recipe: Recipe
        runBlocking {
            recipe = recipeApi.getRecipeDetails(id)
        }
        return recipe
    }

    fun getWithFilters(
        query: String,
        categories: List<String>,
        liked: Boolean,
        minTime: Int,
        maxTime: Int
    ):List<RecipePreview>{
        var recipes: List<RecipePreview>
        runBlocking {
            recipes = recipeApi.getWithFilters(
                id = prefs.getLong("userId", 0),
                query = query,
                categories = categories,
                liked = liked,
                minTime = minTime,
                maxTime = maxTime
            )
        }
        return recipes
    }

    fun toggleLike(id: Long){
        runBlocking {
            recipeApi.toggleLike(id)
        }
    }

    fun deleteRecipe(id: Long){
        viewModelScope.launch {
            recipeApi.deleteRecipe(id)
        }
    }

    fun updateRecipeData(
        recipeId: Long,
        name: String,
        category: String,
        cookTime: Int,
        portions: Int,
        calories: Float,
        proteins: Float,
        fats: Float,
        carbos: Float,
        recipeContent: String,
        liked: Boolean,
        products: List<Product>
    ){
        viewModelScope.launch {
            val userId = prefs.getLong("userId", 0)
            viewModelScope.launch {
                recipeApi.updateRecipeData(recipeId, AddRecipeRequest(
                    userId = userId,
                    name = name,
                    category = category,
                    cookTime = cookTime,
                    portions = portions,
                    calories = calories,
                    proteins = proteins,
                    fats = fats,
                    carbos = carbos,
                    recipeContent = recipeContent,
                    liked = liked,
                    products = products
                ))
            }
        }
    }


    fun addRecipe(
        name: String,
        category: String,
        cookTime: Int,
        portions: Int,
        calories: Float,
        proteins: Float,
        fats: Float,
        carbos: Float,
        recipeContent: String,
        liked: Boolean,
        products: List<Product>,
        context: Context
    ){
        var error: Exception? = null
        Log.d("test", _bitmap.value.toString())
        val multipartBody = imageWork(bitmap.value!!, context)
        runBlocking {
            try {
                recipeApi.addRecipe(
                    AddRecipeRequest(
                        prefs.getLong("userId", 0), name, category, cookTime,
                        portions, calories, proteins, fats,
                        carbos, recipeContent, liked, products
                    ),
                    multipartBody
                )
            } catch (e: Exception){
                error = e
                Log.d("Add2Exc", e.toString())
            }
        }
        if (error != null) {
            throw error as Exception
        }
    }

    fun updateRecipeImage(recipeId: Long, image: Bitmap, context: Context): String{
        var avatar: String
        val multipartBody = imageWork(image, context)
        runBlocking {
            avatar = recipeApi.updateAvatar(recipeId, multipartBody).image
        }
        return avatar
    }

    private fun imageWork (image: Bitmap, context: Context) : MultipartBody.Part{
        var multipartBody: MultipartBody.Part?
        runBlocking {
            //Создаем пустой файл
            val file = File(context.cacheDir, "avatar")
            withContext(Dispatchers.IO) {
                file.createNewFile()
            }
            //Записываем Image в файл
            val bos = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val bitmapData = bos.toByteArray()
            val fos = withContext(Dispatchers.IO) {
                FileOutputStream(file)
            }
            withContext(Dispatchers.IO) {
                fos.write(bitmapData)
                fos.flush()
                fos.close()
            }

            multipartBody = MultipartBody.Part.createFormData(
                "image", "avatar",
                file.asRequestBody("image/*".toMediaTypeOrNull())
            )
        }
        Log.d("multipart", multipartBody.toString())
        return multipartBody!!
    }
}
