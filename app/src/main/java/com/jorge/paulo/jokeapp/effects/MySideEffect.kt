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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object INC { var i = 0 }

/*
O valor da variável inão será o esperado após a recomposição.
 Isso pode ser pior se você precisar fazer uma chamada de
 rede para atualizar a interface do usuário dentro da função que
  pode ser composta sem fornecer um ambiente seguro para lidar com o efeito colateral.
 */
@Composable
fun MySideEffect(){
    val count  = remember {
        INC.i
    }
    SideEffect {
        INC.i++ // incrementing this variable here will cause a side-effect
    }

    Button(onClick = {
        Log.d("INC", " ->> ${INC.i}")
        INC.i++
    }){
        Text(text = "Click $count")
    }
}