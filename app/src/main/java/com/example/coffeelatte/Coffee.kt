package com.example.coffeelatte

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coffee(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
