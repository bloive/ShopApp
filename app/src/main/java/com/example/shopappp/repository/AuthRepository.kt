package com.example.shopappp.repository

import com.example.shopappp.network.Resource
import com.example.shopappp.ui.login.LoginResponse
import com.example.shopappp.ui.register.RegisterResponse

interface AuthRepository {
    suspend fun login(email: String, password: String) : Resource<LoginResponse>
    suspend fun register(email: String, password: String, fullName: String) : Resource<RegisterResponse>
}