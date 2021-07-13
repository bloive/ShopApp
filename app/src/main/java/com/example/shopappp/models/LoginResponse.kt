package com.example.shopappp.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("userId")
    val user_id: Int?,
    val token: String?
)