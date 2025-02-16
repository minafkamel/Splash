package com.helloclue.androidassignment.presentation

sealed class UiState<out T> {
    data object Init : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data object Loading : UiState<Nothing>()
    data class Error(val message: String) : UiState<Nothing>()
}