package com.hello.splash.ui.photos

import com.hello.splash.ui.photos.add.FloatingButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.hello.splash.ui.photos.grid.Photos

@Composable
fun PhotosScreen(navController: NavHostController) {
    Photos(navController)
    FloatingButton()
}