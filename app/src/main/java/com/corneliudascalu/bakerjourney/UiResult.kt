package com.corneliudascalu.bakerjourney

sealed class UiResult<T> {

    data class Success<T>(val value: T) : UiResult<T>()
    class Loading<T> : UiResult<T>()
    data class Failure<T>(val throwable: Throwable) : UiResult<T>()
}