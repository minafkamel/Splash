package com.hellosplash.androidassignment.presentation.photos.grid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.hellosplash.androidassignment.presentation.UiState
import com.hellosplash.androidassignment.presentation.details.Dialog
import com.hellosplash.androidassignment.presentation.photos.PhotosViewModel

@Composable
fun Photos(photosViewModel: PhotosViewModel = viewModel()) {
    val loadStoredUrlsState by photosViewModel.loadStoredUrlsUiState.collectAsState()
    val clickedId = remember { mutableStateOf("") }

    Progress()
    Error()
    Dialog(clickedId)

    when (val state = loadStoredUrlsState) {
        is UiState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.data.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .testTag("card_item"),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = state.data[index].imageUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        clickedId.value = state.data[index].photoId
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

        else -> {}
    }
}
