package com.example.pokemonapp.presontation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokemonapp.presontation.viewmodel.SharedViewModel

@Composable
fun PokemonDetailsScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    sharedViewModel.pokemon?.let { pokemon ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

                .background(color = sharedViewModel.color)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {

                    var isVisible by remember {
                        mutableStateOf(false)
                    }
                    LaunchedEffect(key1 = Unit, block = {
                        isVisible = true
                    })
                    val targetValue = 200.dp
                    val targetSizeAnimation = animateDpAsState(targetValue =  if(isVisible) targetValue else 0.dp,
                        animationSpec = tween(
                            durationMillis = 1000
                        )
                    )

                    AsyncImage(
                        model = pokemon.pokemonImage, contentDescription = "",
                        modifier = Modifier.size(
                            targetSizeAnimation.value
                        )
                    )
                    AnimatedVisibility(visible = isVisible, enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 2000
                        )
                    )) {
                        Text(
                            text = pokemon.pokemonName, style = TextStyle(
                                color = Color.Black,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                            )
                        )
                    }

                }
            }
            item {
                Surface(
                    modifier = Modifier
                        .fillParentMaxWidth().fillParentMaxHeight(0.7f),
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                    elevation = 10.dp,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 5.dp, start = 10.dp, end = 10.dp),

                        horizontalAlignment = Alignment.Start
                    ) {
                        val pokemonPowersStates = listOf(
                            pokemon.pokemonHp,
                            pokemon.pokemonSpeed,
                            pokemon.pokemonAttack,
                            pokemon.pokemonSpecialAttack,
                            pokemon.pokemonDefense,
                            pokemon.pokemonSpecialDefense,
                        )

                        (0..5).forEach {

                            var isVisible by remember {
                                mutableStateOf(false)
                            }

                            LaunchedEffect(key1 = it, block = {
                                isVisible = true
                            })


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AnimatedVisibility(
                                    visible = isVisible, enter = fadeIn(
                                        animationSpec = tween(
                                            durationMillis = 3000
                                        )
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier.width(70.dp),
                                        text = getPokemonPowerStateNameByIndex(it),
                                        maxLines = 1,
                                        color = Color.Gray.copy(
                                            alpha = 0.5f,
                                        ),
                                        overflow = TextOverflow.Ellipsis,
                                        style = TextStyle(
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.Bold,

                                            )
                                    )
                                }
                                Spacer(modifier = Modifier.width(20.dp))

                                AnimatedVisibility(
                                    visible = isVisible, enter = fadeIn(
                                        animationSpec = tween(
                                            durationMillis = 3000
                                        )
                                    )
                                ) {
                                    Text(
                                        text = "${pokemonPowersStates[it].toFloat()}",
                                        color = Color.Black,
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.width(20.dp))
                                LinearProgress(targetValue = pokemonPowersStates[it].toFloat() / 100f)
                            }
                        }
                    }


                }

            }
        }
    }


}


fun getPokemonPowerStateNameByIndex(i: Int): String =
    when (i) {
        0 -> "HP"
        1 -> "SPEED"
        2 -> "ATTACK"
        3 -> "SP ATTACK"
        4 -> "DEFENSE"
        5 -> "SP DEFENSE"
        else -> "UNKNOWN"
    }


@Composable
fun LinearProgress(
    targetValue: Float
) {

    var isAnimated by remember {
        mutableStateOf(false)
    }
    val targetValueAnimation = animateFloatAsState(
        targetValue = if (isAnimated) targetValue else 0f, animationSpec = tween(
            durationMillis = 1500,
            easing = LinearEasing
        )
    )

    LaunchedEffect(key1 = Unit, block = {
        isAnimated = true
    })



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(targetValueAnimation.value)
                .height(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Red)
        )
    }
}

