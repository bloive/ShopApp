package com.example.shopappp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopappp.models.RegisterResponse
import com.example.shopappp.network.Resource
import com.example.shopappp.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepo: AuthRepository
) : ViewModel() {

    private val _registerLiveData = MutableLiveData<Resource<RegisterResponse>>()
    val registerResponseLiveData: LiveData<Resource<RegisterResponse>> = _registerLiveData

    fun register(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _registerLiveData.postValue(Resource.loading(true))
                registerUser(email, password, fullName)
            }
        }
    }

    private suspend fun registerUser(email: String, password: String, fullName: String) {
        val result = authRepo.register(email, password, fullName)
        _registerLiveData.postValue(result)
    }
}