package com.example.shopappp.network

data class Resource<T>(
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
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, false)
        }

        fun <T> loading(loading: Boolean): Resource<T> {
            return Resource(Status.LOADING, null, null, loading)
        }

        fun <T> error(message: String): Resource<T> {
            return Resource(Status.ERROR, null, message, false)
        }
    }
}
