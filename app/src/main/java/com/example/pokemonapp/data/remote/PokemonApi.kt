package com.example.pokemonapp.data.remote

import com.example.pokemonapp.common.Constants
import com.example.pokemonapp.data.remote.dto.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApi {
    @GET(Constants.POKEMON_END_POINT)
    suspend fun getPokemonResponse(@Path("limit") limit:Int ):List<PokemonDto>
}