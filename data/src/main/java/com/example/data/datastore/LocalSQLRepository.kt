package com.example.data.datastore

import com.example.entity.localmodels.PokemonData

interface LocalSQLRepository {
    suspend fun addUpdateDataDB(item: PokemonData)
    fun geDataListDB(): PokemonData
}