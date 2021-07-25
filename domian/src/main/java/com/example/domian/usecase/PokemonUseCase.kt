package com.example.domian.usecase

import com.example.data.datastore.PokemonRepository
import com.example.domian.interactors.PokemonInteractor
import com.example.domian.utils.toPokemonInfoData
import com.example.entity.CallException
import com.example.entity.Constants
import com.example.entity.Result
import com.example.entity.localmodels.PokemonInfo

class PokemonUseCase(private val getDataRepository: PokemonRepository) :
    PokemonInteractor {
    override suspend fun getPokemonResponse(pokemonUrl: String): Result<PokemonInfo> {
        return when (val apiData = getDataRepository.getPokemonResponse(pokemonUrl)) {
            is Result.Success -> {
                val mappingData = apiData.data?.toPokemonInfoData()
                Result.Success(mappingData)
            }
            else -> {
                Result.Error(
                    CallException(
                        Constants.ERROR_NULL_DATA,
                        null,
                        "Can't load User into server"
                    )
                )
            }
        }
    }

}