package com.helloclue.androidassignment.presentation.photos.grid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.helloclue.androidassignment.common.ui.CommonError
import com.helloclue.androidassignment.presentation.UiState
import com.helloclue.androidassignment.presentation.photos.PhotosViewModel

@Composable
fun Error(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState = photosViewModel.loadStoredUrlsUiState.collectAsState()

    if (uiState.value is UiState.Error) {
        val errorMessage = (uiState.value as UiState.Error).message
        CommonError(errorMessage)
    }
}
