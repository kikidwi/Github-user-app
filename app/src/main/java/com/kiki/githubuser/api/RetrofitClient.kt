package com.kiki.githubuser.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitClient {
    private val URL = "https://api.github.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance = retrofit.create(Api::class.java)
}