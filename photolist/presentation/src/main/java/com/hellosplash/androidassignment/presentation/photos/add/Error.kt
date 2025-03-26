package com.hellosplash.androidassignment.presentation.photos.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hellosplash.androidassignment.common.ui.CommonError
import com.hellosplash.androidassignment.presentation.UiState
import com.hellosplash.androidassignment.presentation.photos.PhotosViewModel

@Composable
fun Error(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState by photosViewModel.addPhotoUiState.collectAsState()

    when (val state = uiState) {
        is UiState.Error -> CommonError(state.message)
        else -> {}
    }
}
