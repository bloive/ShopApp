package com.example.shopappp.repository.auth

import com.example.shopappp.models.RegisterResponse
import com.example.shopappp.network.AuthService
import com.example.shopappp.network.Resource
import com.example.shopappp.ui.auth.login.LoginResponse
import com.google.gson.Gson
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService): AuthRepository {
    override suspend fun login(email: String, password: String): Resource<LoginResponse> {
        return try {
            val response = authService.login(email, password)
            if (response.isSuccessful) {
                Resource.success(response.body()!!)
            } else {
                Resource.error(
                    response.errorBody()!!.string(),
                    errorResult?.error ?: "something wrong"
                )
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString(), errorResult?.error ?: "something wrong")
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): Resource<RegisterResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun completeProfile(userID: String): Resource<CompleteProfile> {
        return try {
            val response = authService.completeProfileStatus(userID)
            if (response.isSuccessful) {
                Resource.success(response.body()!!)
            } else {
                val errorModel = Gson().fromJson(response.errorBody()!!.string(), Error::class.java)
                Resource.error(errorModel.message)
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString())
        }
    }


}