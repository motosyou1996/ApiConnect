package com.example.apiconnect.repository

import com.example.apiconnect.api.Service

class MainRepository constructor(private val service:Service){
    suspend fun index() = service.index()
}