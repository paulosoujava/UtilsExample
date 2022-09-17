package com.jorge.paulo.jokeapp.biometria

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.jorge.paulo.jokeapp.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ViewBiometric() {
    val context = LocalContext.current
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Biometric Authentication")
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){
                TextButton(
                    onClick = {
                        val result = Biometric.status(context)
                        Toast.makeText(context, if(result) "Available" else "Not Available", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Availability Status"
                    )
                }
                TextButton(
                    onClick = {
                        Biometric.authenticate(
                            context as FragmentActivity,
                            title = "Biometric Authentication",
                            subtitle = "Authenticate to proceed",
                            description = "Authentication is must",
                            negativeText = "Cancel",
                            onSuccess = {
                                coroutineScope.launch {
                                    Toast.makeText(
                                        context,
                                        "Authenticated successfully",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                            },
                            onError = { errorCode, errorString->
                                coroutineScope.launch {
                                    Toast.makeText(
                                        context,
                                        "Authentication error: $errorCode, $errorString",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        ) {
                            coroutineScope.launch {
                                Toast.makeText(
                                    context,
                                    "Authentication failed",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Authenticate"
                    )
                }
            }
        }
    }
}

