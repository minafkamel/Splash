package com.hello.splash.ui.photos.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hello.splash.common.ui.composables.CommonProgress
import com.hello.splash.ui.UiState
import com.hello.splash.ui.photos.PhotosViewModel


@Composable
fun Progress(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState = photosViewModel.addPhotoUiState.collectAsState()

    if (uiState.value is UiState.Loading) {
        CommonProgress()
    }
}