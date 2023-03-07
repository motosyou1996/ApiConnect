package com.example.apiconnect

import android.util.Log
import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service{/**建立資料Model*/
    data class PostRequest(
       @SerializedName("ATTRIBUTES") val attributes: String,
       @SerializedName("C_ID") val c_Id: String,
       @SerializedName("C_NAME") val c_Name: String,
       @SerializedName("NAME_SURVEY") val name_Survey: String,
       @SerializedName("V_NAME") val v_Name: String,
       @SerializedName("PERIODS_VEGETATIVE_GROWTH") val periods_Vegetative_Growth: Int,
       @SerializedName("PERIODS_FLOWERING") val periods_Flowering: Int,
       @SerializedName("PERIODS_FRUIT_DEVELOPMENT") val periods_Fruit_Development: Int,
       @SerializedName("PERIODS_HARVEST") val periods_Harvest: Int,
       @SerializedName("DAY_FERTILITY") val day_Fertility: Int,
    )
    data class PostResponse(
        @SerializedName("contentType") val contentType:String,
        @SerializedName("isImage") val isImage:Boolean,
        @SerializedName("data") val data:List<PostRequest>,
        @SerializedName("id") val id:String,
        @SerializedName("message") val message:String?,
        @SerializedName("success") val success:Boolean,
    )

    @GET("/api/service/get/a5adfb5a-708e-443f-a2da-acb80a7fed61")/**相對路徑*/
    fun index(): Call<PostResponse>
}
object Server{/**產生Retrofit物件*/
    private const val URL = "https://api.kcg.gov.tw"    
        private val client = OkHttpClient.Builder().build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory((GsonConverterFactory.create()))
            .client(client)
            .build()
}