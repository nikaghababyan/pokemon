package com.example.data.datastore

import com.example.entity.Result
import com.example.entity.responcemodel.PokemonInfoResponse


interface PokemonRepository {
    suspend fun getPokemonResponse(pokemonUrl: String): Result<PokemonInfoResponse>
}