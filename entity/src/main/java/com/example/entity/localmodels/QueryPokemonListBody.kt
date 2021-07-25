package com.example.entity.localmodels

data class QueryPokemonListBody(
    private val _offset: Int,
    private val _limit: Int
) {
    val offset: Int
        get() = _offset
    val limit: Int
        get() = _limit

}