package com.hello.splash.presentation.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hello.splash.domain.photos.AddRandomPhotoUseCase
import com.hello.splash.domain.photos.GetPhotosUseCase
import com.hello.splash.common.domain.Resource
import com.hello.splash.presentation.UiState
import com.hello.splash.presentation.photos.grid.PhotoUi
import com.hello.splash.presentation.photos.grid.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val addRandomPhotoUseCase: AddRandomPhotoUseCase,
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val _addPhotoUIState = MutableStateFlow<UiState<Unit>>(UiState.Init)
    val addPhotoUiState: StateFlow<UiState<Unit>> get() = _addPhotoUIState

    private val _loadStoredUrlsUiState = MutableStateFlow<UiState<List<PhotoUi>>>(UiState.Loading)
    val loadStoredUrlsUiState: StateFlow<UiState<List<PhotoUi>>> get() = _loadStoredUrlsUiState

    init {
        viewModelScope.launch {
            getPhotosUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _loadStoredUrlsUiState.value =
                            UiState.Success(resource.data.map { it.toUiModel() })
                    }

                    else -> {
                        _loadStoredUrlsUiState.value = UiState.Error("Error loading photos urls")
                    }
                }
            }
        }
    }

    fun addPhotoClicked() {
        viewModelScope.launch {
            _addPhotoUIState.value = UiState.Loading
            delay(500) // To simulate loading

            val resource = addRandomPhotoUseCase.invoke()
            when (resource) {
                is Resource.Error -> {
                    _addPhotoUIState.value = UiState.Error("Error adding photo")
                }

                is Resource.Success -> {
                    _addPhotoUIState.value = UiState.Success(Unit)
                }
            }
        }
    }
}