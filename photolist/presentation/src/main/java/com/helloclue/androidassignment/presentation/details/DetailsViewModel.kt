package com.helloclue.androidassignment.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloclue.androidassignment.domain.Resource
import com.helloclue.androidassignment.domain.details.GetDetailsUseCase
import com.helloclue.androidassignment.presentation.UiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val id: String,
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _detailsUiState = MutableStateFlow<UiState<DetailsUi>>(UiState.Init)
    val detailsUiState: StateFlow<UiState<DetailsUi>> get() = _detailsUiState

    init {
        viewModelScope.launch {
            getDetailsUseCase.invoke(id).collectLatest { resource ->
                if (resource is Resource.Success) {
                    _detailsUiState.value =
                        UiState.Success(resource.data.toUiModel())
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): DetailsViewModel
    }


    @AssistedFactory
    interface DetailsViewModelFactory {
        fun create(id: String): DetailsViewModel
    }
}
