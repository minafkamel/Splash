package com.hello.splash.ui.photos.delete

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hello.splash.ui.R

@Composable
fun DeleteDialog(
    photoIdState: MutableState<String>,
    deleteViewModel: DeleteViewModel = hiltViewModel()
) {

    if (photoIdState.value.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = { photoIdState.value = "" },
            text = {
                Text(
                    stringResource(R.string.delete_are_you_sure),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    deleteViewModel.yesDeletePhotoClicked(photoIdState.value)
                    photoIdState.value = ""
                }) {
                    Text(text = stringResource(R.string.delete_yes))
                }
            }
        )
    }
}
