package com.example.pokemonapp.domaine.models

data class Pokemon(
    val id: Int,
    val pokemonName: String,
    val pokemonImage: String,
    val pokemonAttack: Int,
    val pokemonSpecialAttack: Int,
    val pokemonDefense: Int,
    val pokemonSpecialDefense: Int,
    val pokemonHp: Int,
    val pokemonSpeed: Int,
    val isVisible:Boolean = true,
)