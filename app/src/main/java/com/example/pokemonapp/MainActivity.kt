package com.example.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pokemonapp.navigation.PokemonAppNavigation
import com.example.pokemonapp.presontation.theme.PokemonAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonAppTheme {

                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = Color.White) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {

            PokemonAppNavigation()
        }
    }
}

