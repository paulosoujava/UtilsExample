package com.jorge.paulo.jokeapp.effects

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun Timer() {
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        try {
            initTimer(2000) {
                Toast.makeText(context, "The timer ended", Toast.LENGTH_SHORT).show()
            }
        } catch(e: Exception) {
            Toast.makeText(context, "The timer was cancelled: $e", Toast.LENGTH_SHORT).show()
        }
    })
}

suspend fun initTimer(time: Long, onEnd: () -> Unit) {
    delay(timeMillis = time)
    onEnd()
}