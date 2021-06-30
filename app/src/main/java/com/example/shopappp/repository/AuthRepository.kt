package com.example.shopappp.repository

import com.example.shopappp.ui.login.LoginModel
import com.example.shopappp.ui.register.RegisterModel

interface AuthRepository {
    suspend fun login(email: String, password: String) : Result<LoginModel>
    suspend fun register(email: String, password: String, fullName: String) : Result<RegisterModel>
}