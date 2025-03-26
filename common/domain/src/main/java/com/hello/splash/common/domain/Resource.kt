package com.hello.splash.common.domain

sealed class Resource<out T> {
    data class Success<out T>(val data: T ) : Resource<T>()
    data object Error : Resource<Nothing>()
}