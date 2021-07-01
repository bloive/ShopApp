package com.example.shopappp.repository

import com.example.shopappp.network.AuthService
import com.example.shopappp.network.Resource
import com.example.shopappp.ui.login.LoginResponse
import com.example.shopappp.ui.register.RegisterResponse
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService): AuthRepository {
    override suspend fun login(email: String, password: String): Resource<LoginResponse> {
        return try {
            val response = authService.login(email, password)
            if (response.isSuccessful) {
                Resource.success(response.body()!!)
            } else {
                Resource.error(response.errorBody()!!.string())
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString())
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): Resource<RegisterResponse> {
        TODO("Not yet implemented")
    }


}