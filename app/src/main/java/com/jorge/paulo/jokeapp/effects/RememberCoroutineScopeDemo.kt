package com.jorge.paulo.jokeapp.effects

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
/*
LembrarCoroutineScope
Para LaunchedEffect, ele sempre será iniciado na primeira inicialização e não
 parará até que o ciclo de vida do atual composable termine.

Se queremos fazer algo em uma ação ou queremos encerrar explicitamente a
corrotina , podemos usar RememberCoroutineScope .

RememberCoroutineScope é uma função que pode ser composta que retorna um
Coroutine Scope vinculado ao ponto da composição onde é chamado.
O escopo será cancelado quando a chamada sair da Composição.
 */
@Composable
fun RememberCoroutineScopeDemo(){
    val scope = rememberCoroutineScope()
    //  In order to launch a coroutine outside of a composable,
    //  but scoped so that it will be automatically canceled once it leaves the composition
    Button(onClick = {
        scope.launch {
            delay(1000L)
            Log.d("RememberCoroutineScope","")
        }
    }) {}
}