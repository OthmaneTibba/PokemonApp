package com.example.pokemonapp.presontation.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokemonapp.R
import com.example.pokemonapp.domaine.models.Pokemon
import com.example.pokemonapp.navigation.PokemonAppScreens
import com.example.pokemonapp.presontation.viewmodel.PokemonViewModel
import com.example.pokemonapp.presontation.viewmodel.SharedViewModel
import kotlin.random.Random


@Composable
fun PokemonScreen(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    sharedViewModel: SharedViewModel
) {


    val pokemonState = pokemonViewModel.state.collectAsState()

    if (pokemonState.value.isLoading) {
        LoadingProgress()
    } else {
        val pokemonList = pokemonState.value.pokemonList
        Column(modifier = Modifier.fillMaxSize()) {
            AppHeader(pokemonViewModel = pokemonViewModel)
            PokemonVerticalGrid(
                pokemonList = pokemonList,
                navController = navController,
                sharedViewModel = sharedViewModel,
            )

        }
    }

}


@Composable
fun LoadingProgress() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun PokemonVerticalGrid(
    pokemonList: List<Pokemon>,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val colorsList = listOf(
        Color.Blue.copy(alpha = 0.2f),
        Color.Green.copy(alpha = 0.2f),
        Color.Cyan.copy(alpha = 0.2f),
        Color.Yellow.copy(
            alpha = 0.2f,
        ),
        Color.Magenta.copy(
            alpha = 0.2f,
        )
    )


    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(items = pokemonList, key = {
            it.id
        }) { pokemon ->

            val random = Random.nextInt(5)
            PokemonCard(
                color = colorsList[random],
                pokemon = pokemon
            ) {
                sharedViewModel.pokemon = pokemon
                sharedViewModel.color = colorsList[random]
                navController.navigate(PokemonAppScreens.PokemonDetailsScreen.name)
            }

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppHeader(pokemonViewModel: PokemonViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Text(
        modifier = Modifier.padding(start = 10.dp, bottom = 8.dp),
        text = "PokemonApp",
        style = TextStyle(
            color = Color(0xff2C3F3B),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
    )
    Row(
        modifier = Modifier
            .padding(start = 10.dp, bottom = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f),

            label = {
                Text(
                    text = "Search", style = TextStyle(
                        color = Color.Black,
                    )
                )
            },
            value = pokemonViewModel.searchedQuery.value,
            shape = RoundedCornerShape(CornerSize(10.dp)),
            keyboardActions = KeyboardActions(
                onSearch = {
                    pokemonViewModel.searchPokemon(pokemonViewModel.searchedQuery.value)
                    keyboardController?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xffAAC6D0).copy(
                    alpha = 0.4f,
                )
            ),
            onValueChange = {
                    pokemonViewModel.searchedQuery.value = it
                    pokemonViewModel.searchPokemon(it)
            },
        )
        Surface(
            modifier = Modifier
                .width(60.dp)
                .height(50.dp)
                .padding(end = 10.dp),
            color = Color.Black,
            shape = RoundedCornerShape(
                CornerSize(10.dp),

                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_tune_24),
                contentDescription = "",
                tint = Color.White,
            )
        }
    }
}


@Composable
fun PokemonCard(
    pokemon: Pokemon,
    color: Color,
    onItemClick: () -> Unit,
) {



    Surface(
        modifier = Modifier
            .width(80.dp)
            .height(250.dp)
            .padding(10.dp)
            .clickable {
                onItemClick()
            },
        color = color,
        shape = RoundedCornerShape(CornerSize(10.dp)),


        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp),
                contentScale = ContentScale.Crop,
                model = pokemon.pokemonImage,
                contentDescription = null
            )
            Text(
                text = pokemon.pokemonName,
                color = Color.Black,
                style = TextStyle(

                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
        }
    }
}