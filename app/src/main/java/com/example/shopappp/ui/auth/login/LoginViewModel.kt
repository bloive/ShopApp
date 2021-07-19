package com.example.shopappp.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopappp.network.Resource
import com.example.shopappp.repository.auth.AuthRepository
import com.example.shopappp.user_data.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val userPreference: SharedPreference
) : ViewModel() {

    private val _responseLiveData = MutableLiveData<Resource<LoginResponse>>()
    val responseLiveData: LiveData<Resource<LoginResponse>>
        get() = _responseLiveData

    private val _completeProfileLiveData = MutableLiveData<Resource<CompleteProfile>>()
    val completeProfileLiveData: LiveData<Resource<CompleteProfile>> = _completeProfileLiveData

    fun login(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = authRepo.login(email, password)
                _responseLiveData.postValue(result)
            }
        }
    }

    fun saveUserSession(isChecked: Boolean) {
        userPreference.saveUserSession(isChecked)
    }

    fun completeProfile() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = authRepo.completeProfile(userPreference.userId())
                _completeProfileLiveData.postValue(result)
            }
        }
    }
}