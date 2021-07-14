package com.example.shopappp.ui.auth.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("OK")
    val ok: Boolean
)