package com.helloclue.androidassignment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloclue.androidassignment.domain.AddRandomPhotoUseCase
import com.helloclue.androidassignment.domain.GetStoredUrlsUseCase
import com.helloclue.androidassignment.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val addRandomPhotoUseCase: AddRandomPhotoUseCase,
    private val getStoredUrlsUseCase: GetStoredUrlsUseCase
) : ViewModel() {

    private val _addPhotoUIState = MutableStateFlow<UiState<Unit>>(UiState.Init)
    val addPhotoUiState: StateFlow<UiState<Unit>> get() = _addPhotoUIState

    private val _loadStoredUrlsUiState = MutableStateFlow<UiState<List<String?>>>(UiState.Loading)
    val loadStoredUrlsUiState: StateFlow<UiState<List<String?>>> get() = _loadStoredUrlsUiState

    init {
        viewModelScope.launch {
            getStoredUrlsUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _loadStoredUrlsUiState.value = UiState.Success(resource.data!!)
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