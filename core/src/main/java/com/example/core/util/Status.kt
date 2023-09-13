package com.example.core.util

sealed class Status<out T>{
    object Loading: Status<Nothing>()
    data class Success<out T>(val data: T): Status<T>()
    data class Error(val throwable: Throwable): Status<Nothing>()
    override fun toString(): String{
        return when (this){
            Loading -> "Loading"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable = $throwable]"
        }
    }
}
