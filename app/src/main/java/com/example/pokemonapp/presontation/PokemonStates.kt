package com.example.pokemonapp.presontation

import com.example.pokemonapp.domaine.models.Pokemon

data class PokemonStates(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList(),
    val errorMessage: String = ""
)
