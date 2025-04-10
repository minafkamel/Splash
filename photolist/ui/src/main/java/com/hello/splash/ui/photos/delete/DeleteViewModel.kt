package com.hello.splash.ui.photos.delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hello.splash.common.domain.Resource
import com.hello.splash.domain.delete.DeletePhotoUseCase
import com.hello.splash.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteViewModel @Inject constructor(private val deletePhotoUseCase: DeletePhotoUseCase) :
    ViewModel() {

    private val _deletePhotoUiState = MutableStateFlow<UiState<Unit>>(UiState.Init)
    val deletePhotoUiState: StateFlow<UiState<Unit>> get() = _deletePhotoUiState

    fun yesDeletePhotoClicked(id: String) {
        viewModelScope.launch {
            _deletePhotoUiState.value = UiState.Loading
            delay(500) // To simulate loading

            val resource = deletePhotoUseCase(id)
            when (resource) {
                is Resource.Error -> {
                    _deletePhotoUiState.value = UiState.Error("Error deleting photo")
                }

                is Resource.Success -> {
                    _deletePhotoUiState.value = UiState.Success(Unit)
                }
            }
        }
    }
}