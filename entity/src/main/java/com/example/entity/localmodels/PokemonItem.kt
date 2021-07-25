package com.example.entity.localmodels

import androidx.room.ColumnInfo

data class PokemonItem(
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?,
    @ColumnInfo(name = "pokemonInfo")
    var pokemonInfo: PokemonInfo?,
)