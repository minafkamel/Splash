package com.hello.splash.common.ui.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun CommonError(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
}