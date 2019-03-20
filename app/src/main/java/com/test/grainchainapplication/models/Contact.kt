package com.test.grainchainapplication.models

import android.graphics.Bitmap

/**
 * Created by Luis Vargas on 3/19/19.
 */

data class Contact(
    val profile: Bitmap,
    val name: String,
    val lastName: String,
    val age: String,
    val phone: String
)