package com.example.shopappp.repository

import android.util.Log.e
import com.example.shopappp.network.ApiService
import com.example.shopappp.network.Response
import com.example.shopappp.ui.login.LoginModel
import com.example.shopappp.ui.register.RegisterModel
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val apiService: ApiService): AuthRepository {
    override suspend fun login(email: String, password: String): Response<LoginModel> {
        return try {
            val response = apiService.login(email, password)
            if (response.isSuccessful) {
                Response.success(response.body()!!)
            } else {
                Response.error(response.errorBody()!!.string())
            }
        } catch (e: Exception) {
            Response.error(e.message.toString())
        }
    }

    override suspend fun register(email: String, password: String, fullName: String): kotlin.Result<RegisterModel> {
        TODO("Not yet implemented")
    }
}