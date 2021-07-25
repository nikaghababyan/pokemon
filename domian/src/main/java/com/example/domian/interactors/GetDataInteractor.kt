package com.example.domian.interactors

import com.example.entity.Result
import com.example.entity.localmodels.PokemonItem


interface GetDataInteractor {
    suspend fun getDataResponse(): Result<List<PokemonItem>>
    fun sortListBy(
        checkedAttack: Boolean,
        checkedDefence: Boolean,
        checkedHp: Boolean
    ): List<PokemonItem>

    fun loadLocalDataDb(): List<PokemonItem>
}