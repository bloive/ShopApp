package com.example.shopappp.network

import com.example.shopappp.models.RegisterResponse
import com.example.shopappp.ui.auth.login.LoginResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

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

    @POST ("profile")
    @FormUrlEncoded
    suspend fun completeProfileStatus(
        @Field("user_id") userID: String
    ): Response<RegisterResponse>

    @POST ("complete_profile")
    @Multipart
    suspend fun completeProfile(
        @Part("user_id") userID: Int,
        @Part("address") address: String,
        @Part("card_number") card_number: String,
        @Part("card_holder_names") card_holder_names: String,
        @Part("expiry_date") expiry_date: Int,
        @Part("security_code") security_code: Int,
        @Part("floor_apartment") floor_apartment: Boolean,
        @Part("file") file: MultipartBody.Part,
    ): Response<CompleteProfileStatus>
}