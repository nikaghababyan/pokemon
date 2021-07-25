package com.example.data.dataservice.apiservice

import com.example.entity.localmodels.PokemonInfo
import com.example.entity.responcemodel.PokemonDataResponse
import com.example.entity.responcemodel.PokemonInfoResponse
import retrofit2.Response
import retrofit2.http.*

interface AllApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonDataResponse>

    @GET
    suspend fun getPokemonData(
        @Url url: String
    ): Response<PokemonInfoResponse>

}
