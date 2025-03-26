package com.hellosplash.androidassignment.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellosplash.androidassignment.common.domain.Resource
import com.hellosplash.androidassignment.common.ui.mappers.DateFormatter
import com.hellosplash.androidassignment.domain.details.GetDetailsUseCase
import com.hellosplash.androidassignment.presentation.UiState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val id: String,
    private val getDetailsUseCase: GetDetailsUseCase,
    private val dateFormatter: DateFormatter
) : ViewModel() {

    private val _detailsUiState = MutableStateFlow<UiState<DetailsUi>>(UiState.Init)
    val detailsUiState: StateFlow<UiState<DetailsUi>> get() = _detailsUiState

    init {
        viewModelScope.launch {
            getDetailsUseCase.invoke(id).collect { resource ->
                if (resource is Resource.Success) {
                    _detailsUiState.value =
                        UiState.Success(resource.data.toUiModel(dateFormatter))
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): DetailsViewModel
    }
}
