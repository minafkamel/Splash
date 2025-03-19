package com.hellosplash.androidassignment.presentation.photos.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hellosplash.androidassignment.common.ui.CommonError
import com.hellosplash.androidassignment.presentation.UiState
import com.hellosplash.androidassignment.presentation.photos.PhotosViewModel

@Composable
fun Error(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState = photosViewModel.addPhotoUiState.collectAsState()

    if (uiState.value is UiState.Error) {
        val errorMessage = (uiState.value as UiState.Error).message
        CommonError(errorMessage)
    }
}
