package com.jorge.paulo.jokeapp.effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

/*
Um efeito colateral é qualquer coisa que escape do escopo de uma função.

Em funções Composable, um efeito colateral significa uma alteração no estado do aplicativo que ocorre fora de seu escopo.


 */
@Composable
fun SideEffectExample(
    startingDataCounter: MutableState<Int>,
    increaseCounter: MutableState<Int>,
    viewModel: MainViewModel
) {
    val text by remember {
        mutableStateOf("")
    }

    // simple API Call
    LaunchedEffect(key1 = Unit) {
        viewModel.getStartingDataFromAPI("LaunchedEffect")
    }

    val state = viewModel.state.value
    var bgColor = Color.Gray

    // To observe increasing value changes
    when(state){
        ResultState.StartingDataCalled -> {
            startingDataCounter.value++
            bgColor = Color.Yellow
            viewModel.state.value = ResultState.Idle
        }
        ResultState.IncreaseDataCalled -> {
            increaseCounter.value++
            viewModel.state.value = ResultState.Idle
        }
        ResultState.Error -> {}
    }
}