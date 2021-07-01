package com.example.shopappp.network

import com.example.shopappp.ui.login.LoginResponse
import com.example.shopappp.ui.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @POST ("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("Password") password: String
    ): Response<LoginResponse>

    @POST ("register")
    @FormUrlEncoded
    suspend fun register(
        @Field("email") email: String,
        @Field("Password") password: String,
        @Field("full_name") fullName: String
    ): Response<RegisterResponse>
}