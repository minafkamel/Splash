package com.hello.splash.presentation.photos.grid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hello.splash.common.ui.composables.CommonProgress
import com.hello.splash.presentation.UiState
import com.hello.splash.presentation.photos.PhotosViewModel

@Composable
fun Progress(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState by photosViewModel.loadStoredUrlsUiState.collectAsState()

    when (uiState) {
        is UiState.Loading -> CommonProgress()
        else -> {}
    }
}
