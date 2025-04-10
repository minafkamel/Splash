package com.hello.splash.ui.photos.grid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hello.splash.common.ui.composables.CommonProgress
import com.hello.splash.ui.UiState
import com.hello.splash.ui.photos.PhotosViewModel
import com.hello.splash.ui.photos.delete.DeleteViewModel

@Composable
fun Progress(
    photosViewModel: PhotosViewModel = hiltViewModel(),
    deleteViewModel: DeleteViewModel = hiltViewModel()
) {
    val photosUiState by photosViewModel.loadStoredUrlsUiState.collectAsState()
    val deleteUiState by deleteViewModel.deletePhotoUiState.collectAsState()

    when (photosUiState) {
        is UiState.Loading -> CommonProgress()
        else -> {}
    }
    when (deleteUiState) {
        is UiState.Loading -> CommonProgress()
        else -> {}
    }
}
