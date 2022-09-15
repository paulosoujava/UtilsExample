package com.jorge.paulo.jokeapp.effects

import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyComposable(backPressedDispatcher: OnBackPressedDispatcher) {
    val callback = remember {
        object:OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Perform some actions
                Log.d("BACK", "BUM")
            }
        }
    }
    DisposableEffect(backPressedDispatcher) { // restart if dispatcher changes
        backPressedDispatcher.addCallback(callback) //attach the call back here
        onDispose {
            Log.d("BACK", "REMOVE")
            callback.remove() // this prevents memory leaks
        }
    }
}