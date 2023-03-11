package com.easyprog.android.data

sealed class Result<out T : Any> {
    data class SUCCESS<out T : Any>(val data: T) : Result<T>()
    data class ERROR(val e: Exception) : Result<Nothing>()
    object LOADING : Result<Nothing>()
}