package com.hello.splash.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hello.splash.ui.details.DetailsScreen
import com.hello.splash.ui.photos.PhotosScreen

@Composable
fun NavigationHolder() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "photos") {
        composable("photos") { PhotosScreen(navController) }
        composable("details/{photoId}") { backStackEntry ->
            val photoId = backStackEntry.arguments?.getString("photoId") ?: "0"
            DetailsScreen(photoId)
        }
    }
}