package com.helloclue.androidassignment.presentation.photos.grid

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.helloclue.androidassignment.presentation.UiState
import com.helloclue.androidassignment.presentation.photos.PhotosViewModel

@Composable
fun Progress(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState = photosViewModel.loadStoredUrlsUiState.collectAsState()

    if (uiState.value is UiState.Loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
