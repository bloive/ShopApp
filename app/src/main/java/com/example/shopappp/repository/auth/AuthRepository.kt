package com.example.shopappp.repository.auth

import com.example.shopappp.models.RegisterResponse
import com.example.shopappp.network.Resource
import com.example.shopappp.ui.login.LoginResponse

interface AuthRepository {
    suspend fun login(email: String, password: String) : Resource<LoginResponse>
    suspend fun register(email: String, password: String, fullName: String) : Resource<RegisterResponse>
}