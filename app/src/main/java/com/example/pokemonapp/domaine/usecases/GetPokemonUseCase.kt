package com.example.pokemonapp.domaine.usecases

import com.example.pokemonapp.domaine.models.Pokemon
import com.example.pokemonapp.domaine.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(): List<Pokemon> = pokemonRepository.getPokemon()
}