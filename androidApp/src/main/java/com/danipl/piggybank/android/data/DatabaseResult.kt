package com.danipl.piggybank.android.data

sealed class DatabaseResult<out R> {
    data class Success<out T>(val data: T) : DatabaseResult<T>()
    data class Error(val error: Throwable) : DatabaseResult<Nothing>()
}
