package com.example.data.repository

import com.example.data.dataservice.sqlservice.PokemonListDto
import com.example.data.datastore.LocalSQLRepository
import com.example.entity.localmodels.PokemonData
import com.example.entity.localmodels.PokemonItem

class LocalSQLRepositoryImpl(
    private val pokemonListDto: PokemonListDto
) : LocalSQLRepository {
    override suspend fun addUpdateDataDB(item: PokemonData) =
        pokemonListDto.insertDefaultData(item)

    override fun geDataListDB(): PokemonData  = pokemonListDto.getPokemonDataList()

}