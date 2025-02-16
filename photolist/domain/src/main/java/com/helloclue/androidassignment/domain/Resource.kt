package com.helloclue.androidassignment.domain

sealed class Resource<out T> {
    data class Success<out T>(val data: T? = null) : Resource<T>()
    data object Error : Resource<Nothing>()
}