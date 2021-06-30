package com.example.shopappp.ui.login

import androidx.lifecycle.ViewModel
import com.example.shopappp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthRepository) : ViewModel() {

    suspend fun login(email: String, password: String){
        authRepo.login(email, password)
    }
}