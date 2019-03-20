package com.test.grainchainapplication.remote

import com.test.grainchainapplication.models.LoginRequest
import com.test.grainchainapplication.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Luis Vargas on 3/19/19.
 */

interface NetworkApiService {

    @POST("test")
    fun login(@Body data: LoginRequest): Call<LoginResponse>

}