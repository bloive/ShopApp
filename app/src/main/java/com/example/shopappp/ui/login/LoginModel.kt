package com.example.shopappp.ui.login
import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("error")
    val error: String,
    @SerializedName("OK")
    val ok: Boolean
)