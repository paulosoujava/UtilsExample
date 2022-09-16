package com.jorge.paulo.jokeapp.flow



import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class Repository{
    fun get() =  flow {
        repeat(20) {
            delay(3000)
            emit(it)
        }
    }
}

