package com.example.sunflower.network

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()
    data class Failure(val message: String?) : ApiResult<Nothing>()
}
