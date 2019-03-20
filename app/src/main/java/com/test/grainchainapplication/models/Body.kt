package com.test.grainchainapplication.models

/**
 * Created by Luis Vargas on 3/19/19.
 */

data class Body(
    val status: String,
    val auth: Auth,
    val access_token: String
)