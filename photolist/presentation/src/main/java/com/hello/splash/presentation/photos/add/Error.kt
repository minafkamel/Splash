package com.hello.splash.presentation.photos.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hello.splash.common.ui.composables.CommonError
import com.hello.splash.presentation.UiState
import com.hello.splash.presentation.photos.PhotosViewModel

@Composable
fun Error(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState by photosViewModel.addPhotoUiState.collectAsState()

    when (val state = uiState) {
        is UiState.Error -> CommonError(state.message)
        else -> {}
    }
}
