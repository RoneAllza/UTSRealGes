package com.roneallza.utsrealges.api

import com.roneallza.utsrealges.api.services.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://roneallza.web.id/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postService: ApiService = retrofit.create(ApiService::class.java)

}
