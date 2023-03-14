package com.example.pokemonapp.domaine.repository

import com.example.pokemonapp.domaine.models.Pokemon

interface PokemonRepository {
    suspend fun getPokemon():List<Pokemon>

}