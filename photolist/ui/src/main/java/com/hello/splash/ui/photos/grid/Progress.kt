package com.hello.splash.ui.photos.grid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hello.splash.common.ui.composables.CommonProgress
import com.hello.splash.ui.UiState
import com.hello.splash.ui.photos.PhotosViewModel

@Composable
fun Progress(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState by photosViewModel.loadStoredUrlsUiState.collectAsState()

    when (uiState) {
        is UiState.Loading -> CommonProgress()
        else -> {}
    }
}
