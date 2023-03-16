package com.example.apiconnect.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apiconnect.api.Service
import com.example.apiconnect.repository.MainRepository
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository): ViewModel(){

    val errorMessage = MutableLiveData<String>()
    val data = MutableLiveData<Service.PostResponse>()
    var job: Job? = null
    val exceptionHandler =  CoroutineExceptionHandler {_ , throwable -> onError("Exception handled: ${throwable.localizedMessage}")}
    val loading = MutableLiveData<Boolean>()
    fun index(){/**flow*/
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch{
            val response = mainRepository.index()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    data.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String){
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared(){
        super.onCleared()
        job?.cancel()
    }
}