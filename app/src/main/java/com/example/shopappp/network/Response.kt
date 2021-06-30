package com.example.shopappp.network

import com.example.shopappp.ui.login.LoginModel

data class Response<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val loading: Boolean) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Response<T> {
            return Response(Status.SUCCESS, data, null, false)
        }

        fun <T> loading(loading: Boolean): Response<T> {
            return Response(Status.LOADING, null, null, loading)
        }

        fun <T> error(message: String): Response<T>  {
            return Response(Status.ERROR, null, message, false)
        }
    }
}
