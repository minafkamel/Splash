package com.hellosplash.androidassignment.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hellosplash.androidassignment.presentation.R
import com.hellosplash.androidassignment.presentation.UiState

@Composable
fun Dialog(clickedId: MutableState<String>) {
    if (clickedId.value.isNotEmpty()) {
        val viewModel = hiltViewModel<DetailsViewModel, DetailsViewModel.Factory>(
            key = clickedId.value,
            creationCallback = { factory -> factory.create(id = clickedId.value) }
        )
        val state = viewModel.detailsUiState.collectAsState()

        if (state.value is UiState.Success) {
            val details = (state.value as UiState.Success).data
            AlertDialog(
                onDismissRequest = {
                    clickedId.value = ""
                },
                title = { Title() },
                text = { Body(details) },
                confirmButton = { },
                dismissButton = null
            )
        }
    }
}

@Composable
fun Body(details: DetailsUi) {
    Column {
        Row(Modifier.padding(bottom = 16.dp)) {
            Text(details.description)
        }
        Row(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(2f).padding(end = 16.dp),
                text = stringResource(
                    id = R.string.location,
                    details.location.takeIf { it.isNotEmpty() }
                        ?: stringResource(R.string.unknown)))
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.likes, details.likes)
            )
        }
    }

}

@Composable
fun Title() {
    Text(stringResource(id = R.string.details))
}


