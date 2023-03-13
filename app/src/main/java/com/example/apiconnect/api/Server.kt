package com.example.apiconnect.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Server {/**產生Retrofit物件*/
    private const val URL = "https://api.kcg.gov.tw"
    private val client = OkHttpClient.Builder().build()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory((GsonConverterFactory.create()))
        .client(client)
        .build()
}