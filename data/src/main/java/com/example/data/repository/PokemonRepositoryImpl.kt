package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.PokemonRepository
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall
import com.example.entity.Result
import com.example.entity.localmodels.PokemonInfo
import com.example.entity.responcemodel.PokemonInfoResponse
import retrofit2.Response

class PokemonRepositoryImpl(private val allApiService: AllApiService) :
    PokemonRepository {

    override suspend fun getPokemonResponse(pokemonUrl: String): Result<PokemonInfoResponse> =
        makeApiCall({
            getUserPostData(
                allApiService.getPokemonData(pokemonUrl)
            )
        })

    private fun getUserPostData(popularMove: Response<PokemonInfoResponse>): Result<PokemonInfoResponse> =
        analyzeResponse(popularMove)

}