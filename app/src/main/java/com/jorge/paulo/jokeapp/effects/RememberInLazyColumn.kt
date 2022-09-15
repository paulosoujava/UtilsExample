package com.jorge.paulo.jokeapp.effects

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

/*
Embora o lembrete ajude a reter o estado nas
 recomposições, o estado não é retido nas
 alterações de configuração. Para isso, você
 deve usar RememberSaveable. RememberSaveable
 salva automaticamente qualquer valor que possa ser salvo em um Bundle.
 não apenas retido nas alterações de configuração ou rotação, mas também retido na rolagem para fora do item.
 */

@Composable
fun ThisCompose() {
    LazyColumn() {
        items(50) { index ->
            val state = rememberSaveable {
                mutableStateOf(1)
            }
            Text(text = "STATE $index")
        }
    }
}