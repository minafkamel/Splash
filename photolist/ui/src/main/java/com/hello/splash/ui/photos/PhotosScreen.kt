package com.hello.splash.ui.photos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import com.hello.splash.ui.photos.add.FloatingButton
import com.hello.splash.ui.photos.delete.DeleteDialog
import com.hello.splash.ui.photos.grid.Photos

@Composable
fun PhotosScreen(navController: NavHostController) {

    var photoIdState = rememberSaveable { mutableStateOf("") }
    Photos(navController, photoIdState)
    DeleteDialog(photoIdState)
    FloatingButton()
}