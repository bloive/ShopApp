package com.example.shopappp.ui.register
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("OK")
    val oK: Boolean,
    @SerializedName("registered")
    val registered: Boolean
)