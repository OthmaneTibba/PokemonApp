package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.remote.PokemonApi
import com.example.pokemonapp.domaine.models.Pokemon
import com.example.pokemonapp.domaine.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImp @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemon(): List<Pokemon> {
        return pokemonApi.getPokemonResponse(limit = 100).map {
            Pokemon(
                id = it.id,
                pokemonName = it.name,
                pokemonImage = it.image,
                pokemonSpeed = it.stats.speed,
                pokemonAttack = it.stats.attack,
                pokemonSpecialAttack = it.stats.special_attack,
                pokemonDefense = it.stats.defense,
                pokemonSpecialDefense = it.stats.special_defense,
                pokemonHp = it.stats.HP
            )
        }
    }

}