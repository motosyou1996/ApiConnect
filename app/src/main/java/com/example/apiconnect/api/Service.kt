package com.example.apiconnect.api

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Service{/**建立Model*/
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
    suspend fun index(): Response<PostResponse>

    companion object{/**產生Retrofit物件*/
        private const val URL = "https://api.kcg.gov.tw"
        var service : Service? = null
        fun getInstance() : Service{
            if (service == null){
                val client = OkHttpClient.Builder().build()
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory((GsonConverterFactory.create()))
                    .client(client)
                    .build()
                service = retrofit.create(Service ::class.java)
            }
            return service!!
        }
    }

}