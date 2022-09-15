package com.jorge.paulo.jokeapp.effects

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
/*
LaunchedEffect é reiniciado sempre que um parâmetro chave é alterado.
No entanto, pode haver alguns casos em que não queremos que ele seja reiniciado.

Para fazer isso, podemos usar RememberUpdatedState para fazer uma referência
ao estado anterior que pode ser atualizado.

Vamos supor que temos um LandingScreen que navegará para homeScreen depois
 de algum tempo. Se esta tela recompor o cronômetro não deve ser reiniciado.
 */
@Composable
fun RememberUpdatedStateDemo (
    onTimeout : () -> Unit
) {
    // This will always refer to the latest onTimeout function
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)

    // In here i.g. we have a landing screen that triggers a function after some delay,
    // If LandingScreen recomposes, the delay shouldn't start again.
    // To prevent that we are using rememberUpdatedState which stores the last value
    LaunchedEffect(key1 = true) {
        delay (3000)
        updatedOnTimeout()
    }
}
/*
Quando definimos a variável onTimeOut como rememberUpdatedState, ela lembrará o estado anterior e impedirá a reinicialização do cronômetro.


 */