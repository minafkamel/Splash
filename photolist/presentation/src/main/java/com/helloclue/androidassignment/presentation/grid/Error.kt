package com.helloclue.androidassignment.presentation.grid

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.helloclue.androidassignment.presentation.PhotosViewModel
import com.helloclue.androidassignment.presentation.UiState

@Composable
fun Error(photosViewModel: PhotosViewModel = viewModel()) {
    val uiState = photosViewModel.loadStoredUrlsUiState.collectAsState()
    val context = LocalContext.current

    if (uiState.value is UiState.Error) {
        val errorMessage = (uiState.value as UiState.Error).message
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}
