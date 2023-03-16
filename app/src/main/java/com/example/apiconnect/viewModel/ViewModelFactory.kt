package com.example.apiconnect.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiconnect.repository.MainRepository

class ViewModelFactory constructor(private val repository: MainRepository) : ViewModelProvider.Factory {

    override fun <T :ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}