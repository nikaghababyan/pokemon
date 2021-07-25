package com.example.entity.localmodels

import androidx.room.ColumnInfo

data class PokemonInfo(
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "weight")
    val weight: Int?,
    @ColumnInfo(name = "height")
    val height: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "stats")
    val stats: List<Stat>?,
    @ColumnInfo(name = "types")
    val types: List<Type>?
)