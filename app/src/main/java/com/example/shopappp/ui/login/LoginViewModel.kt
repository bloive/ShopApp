package com.example.shopappp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopappp.network.Resource
import com.example.shopappp.repository.AuthRepository
import com.example.shopappp.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthRepository) : ViewModel() {

    private val _responseLiveData = MutableLiveData<Resource<LoginResponse>>()
    val responseLiveData : LiveData<Resource<LoginResponse>>
        get() = _responseLiveData

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = authRepo.login(email, password)
                _responseLiveData.postValue(result)
            }

    }

    }


//    suspend fun login(email: String, password: String){
//        authRepo.login(email, password)
//    }
}