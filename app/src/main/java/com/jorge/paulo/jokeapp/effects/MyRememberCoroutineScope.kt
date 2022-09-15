package com.jorge.paulo.jokeapp.effects

import android.widget.Toast
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerRememberCoroutineScope() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    Button(onClick = {
        Toast.makeText(context, "The timer Started", Toast.LENGTH_SHORT).show()
        coroutineScope.launch {
            try {
                initTimer2(2000) {
                    Toast.makeText(context, "The timer ended", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "The timer was cancelled: $e", Toast.LENGTH_SHORT).show()
            }
        }
    }) {
        Text("Start")
    }
}
suspend fun initTimer2(time: Long, Finish : () -> Unit){
    delay(timeMillis = time)
    Finish()
}