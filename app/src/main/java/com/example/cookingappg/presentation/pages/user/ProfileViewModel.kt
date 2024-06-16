package com.example.cookingappg.presentation.pages.user

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingappg.data.User
import com.example.cookingappg.data.api.UserApi
import com.example.cookingappg.data.api.dto.UpdateUserDataRequest
import com.example.cookingappg.data.local_manager.LocalManager
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
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userApi: UserApi,
    private val prefs: SharedPreferences,
    private val localManager: LocalManager
): ViewModel(){

    private val _bitmap = MutableStateFlow<Bitmap?>(null)
    val bitmap = _bitmap.asStateFlow()

    fun onTakePhoto(context: Context, bitmap: Bitmap?) {
        _bitmap.value = bitmap
        if (bitmap != null) {
            updateUserImage(bitmap, context)
        }
    }

    fun logOut(){
        runBlocking {
            localManager.removeAppEntry()
            prefs.edit().remove("userId").apply()
        }
    }

    fun getUserData():User{
        val userId = prefs.getLong("userId", 0)
        var userData: User
        runBlocking {
            userData = userApi.getUserData(userId)
        }
        return userData
    }

    fun updateUserData(name: String, email: String, oldPassword: String, newPassword: String){
        viewModelScope.launch {
            val userId = prefs.getLong("userId", 0)
            val password = prefs.getString("password", "")
            if(password == oldPassword){
                userApi.updateUserData(UpdateUserDataRequest(userId, name, email, newPassword))
                prefs.edit().putString("password", newPassword).apply()
            } else{
                throw RuntimeException("Иди нафиг Exception")
            }
        }
    }

    fun updateUserImage(image: Bitmap, context: Context): String{
        var avatar: String = ""
        runBlocking {
            val userId = prefs.getLong("userId", 0)

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

            val multipartBody = MultipartBody.Part.createFormData("image", "avatar",
                file.asRequestBody("image/*".toMediaTypeOrNull())
            )

            avatar = userApi.updateAvatar(userId, multipartBody).image
        }
        return avatar
    }
}