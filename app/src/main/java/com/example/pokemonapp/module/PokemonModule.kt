package com.example.pokemonapp.module

import com.example.pokemonapp.common.Constants
import com.example.pokemonapp.data.remote.PokemonApi
import com.example.pokemonapp.data.repository.PokemonRepositoryImp
import com.example.pokemonapp.domaine.repository.PokemonRepository
import com.example.pokemonapp.domaine.usecases.GetPokemonUseCase
import com.example.pokemonapp.presontation.viewmodel.PokemonViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PokemonModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokemonApi::class.java)



    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonApi):PokemonRepository =
        PokemonRepositoryImp(pokemonApi = pokemonApi)

    @Provides
    @Singleton
    fun provideGetPokemonUseCase(pokemonRepository: PokemonRepository):GetPokemonUseCase =
        GetPokemonUseCase(pokemonRepository)


}