package com.clone.metabox.result

import androidx.annotation.Keep
import com.clone.metabox.result.Result.*

@Keep
sealed class Result<out R> {

    @Keep
    data class Success<out T>(val data: T) : Result<T>()
    @Keep
    data class Error(val throwable: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    val isLoading get() = this is Loading
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            Loading -> "Loading"
        }
    }
}

val <T> Result<T>.data: T?
    get() = (this as? Success)?.data

fun <T, R> Result<T>.map(function: ((T) -> R)) : Result<R> {
    return when(this){
        is Loading -> {
            Loading
        }
        is Success -> {
            Success(function.invoke(data))
        }
        is Error -> {
            Error(throwable)
        }
    }
}