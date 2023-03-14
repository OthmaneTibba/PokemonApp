package com.example.pokemonapp.presontation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.domaine.models.Pokemon


class SharedViewModel : ViewModel() {
    var pokemon:Pokemon? = null
    var color:Color = Color.White
}