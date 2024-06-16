package com.example.cookingappg.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    var name: String,
    var productNumber: String
) : Parcelable
