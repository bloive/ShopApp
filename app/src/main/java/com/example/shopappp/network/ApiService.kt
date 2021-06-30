package com.example.shopappp.network

import com.example.shopappp.ui.login.LoginModel
import com.example.shopappp.ui.register.RegisterModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST ("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("Password") password: String
    ): Response<LoginModel>

    @POST ("register")
    @FormUrlEncoded
    suspend fun register(
        @Field("email") email: String,
        @Field("Password") password: String,
        @Field("full_name") fullName: String
    ): Response<RegisterModel>
}