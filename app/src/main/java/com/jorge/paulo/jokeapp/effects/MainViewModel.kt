package com.jorge.paulo.jokeapp.effects

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


sealed interface ResultState{
    object Idle: ResultState
    object StartingDataCalled: ResultState
    object IncreaseDataCalled: ResultState
    object Error: ResultState
}

class MainViewModel : ViewModel() {

    var state =  mutableStateOf<ResultState>(ResultState.Idle)

    init {
        state.value = ResultState.Idle
    }

    fun getStartingDataFromAPI(functionName : String) {
        Log.d(functionName, "GET DATA API CALLED !!!")
        state.value = ResultState.StartingDataCalled
    }

    fun increaseCount(functionName : String) {
        Log.d(functionName, "INCREASE API CALLED !!!")
        state.value = ResultState.IncreaseDataCalled
    }
}