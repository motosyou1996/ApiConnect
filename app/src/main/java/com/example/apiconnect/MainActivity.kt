package com.example.apiconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.apiconnect.api.Service
import com.example.apiconnect.repository.MainRepository
import com.example.apiconnect.viewModel.MainViewModel
import com.example.apiconnect.viewModel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var btnRequest :Button
    private lateinit var apiTxtView :TextView
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRequest = findViewById(R.id.btnRequst)
        apiTxtView = findViewById(R.id.apiTxtView)
        val service = Service.getInstance()
        val mainRepository = MainRepository(service)
        viewModel = ViewModelProvider(this, ViewModelFactory(mainRepository)).get(MainViewModel::class.java)
        viewModel.data.observe(this
        ) { t -> apiTxtView.text = t?.data.toString() }

        btnRequest.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.index()
            }
        }
    }
}
