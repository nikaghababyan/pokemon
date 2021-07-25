package com.example.data.datastore

import com.example.entity.Result
import com.example.entity.localmodels.PokemonData
import com.example.entity.localmodels.QueryPokemonListBody
import com.example.entity.responcemodel.PokemonDataResponse

interface GetDataRepository {
     suspend fun getDataListResponse(queryBody: QueryPokemonListBody): Result<PokemonDataResponse>
}