package com.example.domian.usecase

import com.example.data.datastore.GetDataRepository
import com.example.data.datastore.LocalSQLRepository
import com.example.domian.interactors.GetDataInteractor
import com.example.domian.interactors.PokemonInteractor
import com.example.domian.utils.toPokemonInfoData
import com.example.entity.CallException
import com.example.entity.Constants.Companion.ERROR_NULL_DATA
import com.example.entity.Constants.Companion.EXAPTION_FINISH_PAGINITION
import com.example.entity.Result
import com.example.entity.localmodels.PokemonItem
import com.example.entity.localmodels.QueryPokemonListBody
import com.example.entity.Constants
import com.example.entity.Constants.Companion.POKEMON_LIMIT
import com.example.entity.localmodels.PokemonData
import kotlinx.coroutines.*


class GetDataUseCase(
    private val getDataRepository: GetDataRepository,
    private val localSQLRepository: LocalSQLRepository,
    private val pokemonInteractor: PokemonInteractor
) :
    GetDataInteractor {
    private val pokemonData: MutableList<PokemonItem> = mutableListOf()
    private var totalCount: Int = 0
    private var offsetCount: Int = -1
    private var nextLoadPage: String? = null
    override suspend fun getDataResponse(): Result<List<PokemonItem>> {
        if (offsetCount <= totalCount) {
            if (offsetCount == -1) {
                offsetCount = 0
            } else {
                offsetCount += 30
            }
            return when (val apiData = getDataRepository.getDataListResponse(
                QueryPokemonListBody(
                    offsetCount, POKEMON_LIMIT
                )
            )) {
                is Result.Success -> {

                    apiData.data?.toPokemonInfoData()?.let {
                        it.count?.apply {
                            totalCount = this@apply
                        }
                        it.next?.apply {
                            nextLoadPage = this@apply
                        }
                        it.results?.let {
                            pokemonData.addAll(it)
                        }
                        getPokemonAllData(pokemonData)
                        localSQLRepository.addUpdateDataDB(it)
                    }

                    Result.Success(pokemonData)
                }
                else -> {
                    Result.Error(
                        CallException(
                            ERROR_NULL_DATA,
                            null,
                            "Can't load User into server"
                        )
                    )
                }
            }

        } else {
            return Result.Error(
                CallException(
                    EXAPTION_FINISH_PAGINITION,
                    null,
                    "No more data"
                )
            )
        }

    }

    private suspend fun getPokemonAllData(list: List<PokemonItem>) {
        coroutineScope {
            list.map {
                async(Dispatchers.IO) {
                    it.url?.run {
                        when (val pokemonData = pokemonInteractor.getPokemonResponse(this)) {
                            is Result.Success -> {
                                it.pokemonInfo = pokemonData.data
                            }
                            else -> {
                                Result.Error(
                                    CallException(
                                        ERROR_NULL_DATA,
                                        null,
                                        "Can't load Pokemon into server"
                                    )
                                )
                            }
                        }
                    }
                }
            }.awaitAll()

        }
    }

    override fun sortListBy(
        checkedAttack: Boolean,
        checkedDefence: Boolean,
        checkedHp: Boolean
    ): List<PokemonItem> {
        val sortedList =
            pokemonData.sortedWith(compareBy {
                var attack = 0
                var drfence = 0
                var hp = 0
                it.pokemonInfo?.stats?.forEach {
                    if (checkedAttack) {
                        if (it.stat?.name == Constants.ATTACK) {
                            it.baseStat?.run { attack = this }
                        }
                    }
                    if (checkedDefence) {
                        if (it.stat?.name == Constants.DEFENSE) {
                            it.baseStat?.run { drfence = this }
                        }
                    }
                    if (checkedHp) {
                        if (it.stat?.name == Constants.ATTACK) {
                            it.baseStat?.run { hp = this }
                        }
                    }
                }
                attack.plus(drfence).plus(hp)
            })
        return sortedList
    }

    override fun loadLocalDataDb(): List<PokemonItem> {
        localSQLRepository.geDataListDB().results?.run {
            pokemonData.addAll(this)
        }

        return pokemonData
    }
}