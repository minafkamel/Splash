package com.hellosplash.androidassignment.presentation.photos.grid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hellosplash.androidassignment.common.ui.composables.CommonProgress
import com.hellosplash.androidassignment.presentation.UiState
import com.hellosplash.androidassignment.presentation.photos.PhotosViewModel

@Composable
fun Progress(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState by photosViewModel.loadStoredUrlsUiState.collectAsState()

    when (uiState) {
        is UiState.Loading -> CommonProgress()
        else -> {}
    }
}
