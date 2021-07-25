package com.example.domian.utils


import com.example.entity.Constants.Companion.POKEMON_IMAGE_BASE_URL
import com.example.entity.Constants.Companion.POKEMON_IMAGE_FORMAT
import com.example.entity.localmodels.*
import com.example.entity.responcemodel.*

fun PokemonDataResponse.toPokemonInfoData() = PokemonData(
    id = 100,
    count = count,
    next = next,
    previous = previous,
    results = results?.toLocalPokemonList()
)

private fun List<PokemonListResponse>.toLocalPokemonList(): List<PokemonItem> {
    val pokemonList: MutableList<PokemonItem> = mutableListOf()
    forEach { pokemonListResponse ->
        val bits: List<String>? = pokemonListResponse.url?.split("/")
        val lastOne = bits?.get(bits.size - 2)
        pokemonList.add(
            PokemonItem(
                pokemonListResponse.name,
                pokemonListResponse.url,
                POKEMON_IMAGE_BASE_URL + lastOne + POKEMON_IMAGE_FORMAT, null
            )
        )
    }
    return pokemonList
}

fun PokemonInfoResponse.toPokemonInfoData() = PokemonInfo(
    id = id,
    weight = weight,
    height = height,
    name = name,
    stats = stats?.toState(),
    types = types?.toType()
)

private fun List<StatResponse>.toState(): List<Stat> {
    val userPostList: MutableList<Stat> = mutableListOf()
    forEach {
        userPostList.add(
            Stat(
                it.baseStat,
                it.effort,
                it.stat?.toStateItem()
            )
        )
    }
    return userPostList
}

private fun StatItemResponse.toStateItem() = StatItem(
    name = name,
    url = url
)

private fun List<TypeResponse>.toType(): List<Type> {
    val userPostList: MutableList<Type> = mutableListOf()
    forEach {
        userPostList.add(
            Type(
                it.slot,
                it.type?.toTypeItem()
            )
        )
    }
    return userPostList
}

private fun TypeItemResponse.toTypeItem() = TypeItem(
    name = name,
    url = url
)



