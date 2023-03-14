package com.example.pokemonapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonapp.presontation.screens.PokemonDetailsScreen
import com.example.pokemonapp.presontation.screens.PokemonScreen
import com.example.pokemonapp.presontation.viewmodel.PokemonViewModel
import com.example.pokemonapp.presontation.viewmodel.SharedViewModel

@Composable
fun PokemonAppNavigation(){
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
    val pokemonViewModel: PokemonViewModel = viewModel()
    NavHost(navController = navController, startDestination = PokemonAppScreens.PokemonScreen.name ){
        composable(route = PokemonAppScreens.PokemonScreen.name){
            PokemonScreen(navController = navController , pokemonViewModel = pokemonViewModel, sharedViewModel = sharedViewModel)
        }
        composable(route = PokemonAppScreens.PokemonDetailsScreen.name){
            PokemonDetailsScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
    }
}