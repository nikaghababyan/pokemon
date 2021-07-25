package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.GetDataRepository
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall
import retrofit2.Response
import com.example.entity.Result
import com.example.entity.localmodels.PokemonData
import com.example.entity.localmodels.QueryPokemonListBody
import com.example.entity.responcemodel.PokemonDataResponse

class GetDataRepositoryImpl(private val allApiService: AllApiService) :
    GetDataRepository {

    override suspend fun getDataListResponse(queryBody: QueryPokemonListBody): Result<PokemonDataResponse> =
        makeApiCall({
            getUserData(
                allApiService.getPokemonList(queryBody.offset,queryBody.limit)
            )
        })

    private fun getUserData(popularMove: Response<PokemonDataResponse>): Result<PokemonDataResponse> =
        analyzeResponse(popularMove)
}