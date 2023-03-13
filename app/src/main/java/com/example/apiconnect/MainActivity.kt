package com.example.apiconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.apiconnect.api.Server
import com.example.apiconnect.api.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import java.io.IOException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var btnRequest :Button
    private lateinit var apiTxtView :TextView
    private val tag = MainActivity::class.java.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRequest = findViewById(R.id.btnRequst)
        apiTxtView = findViewById(R.id.apiTxtView)

        btnRequest.setOnClickListener(){
            CoroutineScope(Dispatchers.Main).launch {
                val service : Service = Server.retrofit.create(Service ::class.java)
                service.index().enqueue(object : Callback<Service.PostResponse> {/**抓資料*/
                    override fun onResponse(
                        call: Call<Service.PostResponse>,
                        response: Response<Service.PostResponse>
                    ) {
                        apiTxtView.text = response.body()?.toString()
                    }
                    override fun onFailure(call: Call<Service.PostResponse>, t: Throwable) {
                        Log.d(tag, "error: ${t.message}" ?: "Get some error")
                    }
                })
            }
        }
    }
}