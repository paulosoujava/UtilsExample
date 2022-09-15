package com.jorge.paulo.jokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jorge.paulo.jokeapp.effects.INC
import com.jorge.paulo.jokeapp.effects.MyComposable
import com.jorge.paulo.jokeapp.effects.MySideEffect
import com.jorge.paulo.jokeapp.effects.Timer
import com.jorge.paulo.jokeapp.effects.TimerRememberCoroutineScope

import com.jorge.paulo.jokeapp.ui.theme.JokeAppTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var dataStoreManager: DataStoreManager

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStoreManager = DataStoreManager(this@MainActivity)

        GlobalScope.launch(Dispatchers.IO) {

            delay(2000)
            INC.i = 200 //teste no MySideEffect

            /*dataStoreManager.getFromDataStore().collect {
                dataStoreManager.saveDataStore(PhoneBook("Sharon", "3526281", "Bangalore"))
                //phoneBookDetails.setText(it.name + " " + it.phone +" "+ it.address)
            }*/
        }

        setContent {
            JokeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Timer()
                    //TimerRememberCoroutineScope()
                    //MyComposable(backPressedDispatcher = onBackPressedDispatcher)
                    MySideEffect()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JokeAppTheme {
        Greeting("Android")
    }
}