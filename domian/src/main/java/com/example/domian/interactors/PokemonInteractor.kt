package com.example.domian.interactors

import com.example.entity.Result
import com.example.entity.localmodels.PokemonInfo

interface PokemonInteractor {
    suspend fun getPokemonResponse(pokemonUrl: String): Result<PokemonInfo>
}