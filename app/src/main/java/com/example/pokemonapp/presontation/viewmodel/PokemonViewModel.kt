package com.example.pokemonapp.presontation.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domaine.models.Pokemon
import com.example.pokemonapp.domaine.usecases.GetPokemonUseCase
import com.example.pokemonapp.presontation.PokemonStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PokemonStates())
    val state = _state.asStateFlow()
    private var _tempPokemonList: List<Pokemon> = emptyList()
    var searchedQuery = mutableStateOf("")

    init {
        getPokemonList()
    }


    fun searchPokemon(query: String) {
        var tempList: List<Pokemon> = emptyList()
        _tempPokemonList.map {
            if (it.pokemonName.lowercase().contains(query.lowercase())) {
                tempList += it
            }
        }
        _state.value = PokemonStates(
            pokemonList = tempList
        )
    }

    private fun getPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = PokemonStates(
                    isLoading = true
                )
                val pokemonList = getPokemonUseCase()
                _state.value = PokemonStates(
                    isLoading = false,
                    pokemonList = pokemonList
                )
                _tempPokemonList = pokemonList
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = PokemonStates(
                    isLoading = false,
                    errorMessage = "Error while loading pokemon data please try again later.."
                )
            }
        }


    }

}